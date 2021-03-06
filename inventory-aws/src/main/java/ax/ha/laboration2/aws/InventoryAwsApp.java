package ax.ha.laboration2.aws;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

public final class InventoryAwsApp {

    private static final String GROUP_NAME = "mansn";

    private static final String AWS_ACCOUNT_ID = "292370674225";

    public static void main(final String[] args) {
        final App app = new App();

        final String s3BucketKey;

        if (System.getenv("APPLICATION_JAR_PATH") != null) {
            s3BucketKey = new FileUploadService().uploadFileToS3(GROUP_NAME, System.getenv("APPLICATION_JAR_PATH"));
        }
        else {
            s3BucketKey = "inventory-0.0.2-SNAPSHOT.jar";
        }

        final ApplicationStack applicationStack = new ApplicationStack(app, new StackProps.Builder()
                .env(Environment.builder()
                        .account(AWS_ACCOUNT_ID)
                        .region(System.getenv("CDK_DEFAULT_REGION"))
                        .build())
                .stackName(GROUP_NAME + "-InventoryApplicationStack")
                .build(),GROUP_NAME, s3BucketKey);

        app.synth();
    }
}
