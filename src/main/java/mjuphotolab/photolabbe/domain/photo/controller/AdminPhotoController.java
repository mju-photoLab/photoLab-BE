package mjuphotolab.photolabbe.domain.photo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.aws.service.AwsS3Service;
import mjuphotolab.photolabbe.domain.photo.service.PhotoService;

@RestController
@RequestMapping("api/admin/photos")
@RequiredArgsConstructor
public class AdminPhotoController {

	private final PhotoService photoService;
	private final AwsS3Service awsS3Service;

	@DeleteMapping("/{photoId}")
	public String deleteFile(
		@PathVariable Long photoId,
		@RequestParam String fileName) {
		photoService.deletePhoto(photoId);
		return awsS3Service.deleteFile(fileName);
	}

}
