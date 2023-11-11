package mjuphotolab.photolabbe.aws.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsS3Service {

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private final AmazonS3 amazonS3Client;

	public List<String> uploadImages(String photoDomainType, List<MultipartFile> multipartFiles) {
		List<String> uploadFileUrls = new ArrayList<>();

		String uploadFilePath = photoDomainType + "/";

		multipartFiles.forEach(file -> {
			String originalFileName = file.getOriginalFilename();
			assert originalFileName != null;
			String uploadFileName = getUuidFileName(originalFileName);
			String uploadFileUrl = "";

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(file.getSize());
			objectMetadata.setContentType(file.getContentType());

			try (InputStream inputStream = file.getInputStream()) {

				String keyName = uploadFilePath + uploadFileName;

				amazonS3Client.putObject(new PutObjectRequest(bucket, keyName, inputStream, objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));

				uploadFileUrl = amazonS3Client.getUrl(bucket, keyName).toString();
			} catch (IOException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
			}

			uploadFileUrls.add(uploadFileUrl);
		});

		return uploadFileUrls;
	}

	public String deleteFile(String fileName) {

		String result = "success";

		try {
			amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
		} catch (Exception e) {
			log.debug("Delete File failed", e);
		}

		return result;
	}

	public String getUuidFileName(String fileName) {
		String ext = fileName.substring(fileName.indexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}
}


