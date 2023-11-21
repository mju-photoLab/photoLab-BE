package mjuphotolab.photolabbe.domain.photo.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class PhotoCompetitionRequest {

	private Long parentId;
	private String title;
	private String description;
	private String studentNumber;
	private String photographer;

	public Photo toEntity(Competition competition, String imagePath, User user) {
		return Photo.builder()
			.competition(competition)
			.user(user)
			.imagePath(imagePath)
			.title(title)
			.description(description)
			.studentNumber(studentNumber)
			.photographer(photographer)
			.build();
	}
}
