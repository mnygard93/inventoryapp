package ax.ha.laboration2.aws;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadService {

    public String uploadFileToS3(final String groupName, final String filepath) {
        final Path path = Paths.get(filepath);
        final String keyName = path.getFileName().toString();
        S3Client.create().putObject(PutObjectRequest.builder()
                        .bucket(groupName + "-storage")
                        .key(keyName)
                        .build(),
                RequestBody.fromFile(path));
        return keyName;
    }
}
