package mjuphotolab.photolabbe.domain.photo.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class InitExhibitionPhotoDto {

	private String title;
	private String description;
	private String studentNumber;

	public static Photo toEntity(Exhibition exhibition, User user, String imagePath) {
		return Photo.builder()
			.user(user)
			.exhibition(exhibition)
			.title("title")
			.description("description")
			.imagePath(imagePath)
			.studentNumber("studentNumber")
			.build();
	}

}
