package mjuphotolab.photolabbe.domain.photo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.aws.service.AwsS3Service;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.photo.controller.dto.request.PhotoRequest;
import mjuphotolab.photolabbe.domain.photo.service.PhotoService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@RequestMapping("api/admin/photos")
@RequiredArgsConstructor
public class AdminPhotoController {

	private final PhotoService photoService;
	private final AwsS3Service awsS3Service;

	@PostMapping("/newCompetitionPhoto")
	public void registerCompetitionPhoto(@RequestPart MultipartFile multipartFile, @RequestPart PhotoRequest photoRequest,
		@LoginUser User user) {
		photoService.registerCompetitionPhoto(multipartFile, photoRequest, user);
	}

	@DeleteMapping("/{photoId}")
	public String deleteFile(
		@PathVariable Long photoId,
		@RequestParam String fileName) {
		photoService.deletePhoto(photoId);
		return awsS3Service.deleteFile(fileName);
	}

	@PatchMapping("{photoId}")
	public Long updatePhoto(@PathVariable Long photoId, @RequestBody PhotoRequest photoRequest) {
		return photoService.updatePhoto(photoId, photoRequest);
	}

}
