package ax.ha.laboration2.aws;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.elasticbeanstalk.CfnApplication;
import software.amazon.awscdk.services.elasticbeanstalk.CfnApplicationVersion;
import software.amazon.awscdk.services.elasticbeanstalk.CfnEnvironment;
import software.amazon.awscdk.services.route53.CfnRecordSet;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.IBucket;

import java.util.Arrays;
import java.util.Collections;

public class ApplicationStack extends Stack {

    private static final String SOLUTION_STACK_NAME_JAVA_11 = "64bit Amazon Linux 2 v3.1.2 running Corretto 11";

    public ApplicationStack(final Construct scope, final StackProps props, final String groupName,
                            final String s3BucketKey) {

        super(scope, "InventoryBackendStack", props);


        final CfnApplication cfnApplication = CfnApplication.Builder.create(this, "InventoryApplication")
                .applicationName(groupName + "-InventoryApp")
                .build();

        final IBucket iBucket = Bucket.fromBucketArn(this, "BucketId",
                Fn.importValue(groupName + "-StorageBucketArn"));


        final CfnApplicationVersion cfnApplicationVersion =
                CfnApplicationVersion.Builder.create(this, "InventoryApplicationVersion")
                        .applicationName(Fn.ref(cfnApplication.getLogicalId()))
                        .description(groupName + "'s Application")
                        .sourceBundle(new CfnApplicationVersion.SourceBundleProperty.Builder()
                                .s3Bucket(iBucket.getBucketName())
                                .s3Key(s3BucketKey)
                                .build())
                        .build();

        final IVpc vpc = Vpc.fromLookup(this, "DefaultVpc", VpcLookupOptions.builder()
                .isDefault(true).build());


        final CfnSecurityGroup securityGroup = CfnSecurityGroup.Builder.create(this, "InventoryAppSecurityGroup")
                .groupName(groupName + "-SecurityGroup")
                .vpcId(vpc.getVpcId())
                .groupDescription("Security group for Inventory-application")
                .build();

        final CfnSecurityGroupIngress securityGroupIngress = CfnSecurityGroupIngress.Builder.create(this, "SecurityGroupIngress")
                .groupId(Fn.getAtt(securityGroup.getLogicalId(), "GroupId").toString())
                .ipProtocol("tcp")
                .fromPort(80)
                .toPort(80)
                .cidrIp("128.0.0.0/1")
                .build();

        final CfnEnvironment cfnEnvironment = CfnEnvironment.Builder.create(this, "InventoryAppEnvironment")
                .optionSettings(Arrays.asList(
                        CfnEnvironment.OptionSettingProperty.builder()
                                .namespace("aws:autoscaling:launchconfiguration")
                                .optionName("IamInstanceProfile")
                                .value("ha-elasticbeanstalk-ec2-role")
                                .build(),
                        CfnEnvironment.OptionSettingProperty.builder()
                                .namespace("aws:ec2:instances")
                                .optionName("InstanceTypes")
                                .value("t3.nano")
                                .build(),
                        CfnEnvironment.OptionSettingProperty.builder()
                                .namespace("aws:autoscaling:launchconfiguration")
                                .optionName("SecurityGroups")
                                .value(securityGroup.getGroupName())
                                .build(),
                        CfnEnvironment.OptionSettingProperty.builder()
                                .namespace("aws:elasticbeanstalk:environment")
                                .optionName("EnvironmentType")
                                .value("SingleInstance")
                                .build()))
                .solutionStackName(SOLUTION_STACK_NAME_JAVA_11)
                .environmentName(groupName + "-Environment")
                .applicationName(groupName + "-InventoryApp")
                .versionLabel(Fn.ref(cfnApplicationVersion.getLogicalId()))
                .build();


        final CfnRecordSet cfnRecordSet = CfnRecordSet.Builder.create(this, "InventoryAppRecordSet")
                .name("inventory.cloud-ha.com")
                .type("A")
                .hostedZoneId("Z0413857YT73A0A8FRFF")
                .resourceRecords(Collections.singletonList(cfnEnvironment.getAttrEndpointUrl()))
                .ttl("300")
                .build();


    }

}
