package mjuphotolab.photolabbe.domain.photo.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class PhotoExhibitionRequest {

	private Long parentId;
	private String title;
	private String description;
	private String studentNumber;

	public Photo toEntity(Exhibition exhibition, String imagePath, User user) {
		return Photo.builder()
			.exhibition(exhibition)
			.user(user)
			.imagePath(imagePath)
			.title(title)
			.description(description)
			.studentNumber(studentNumber)
			.build();
	}
}
