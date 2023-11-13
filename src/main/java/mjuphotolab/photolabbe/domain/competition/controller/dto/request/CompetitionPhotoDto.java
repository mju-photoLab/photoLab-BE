package mjuphotolab.photolabbe.domain.competition.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class CompetitionPhotoDto {

	private String title;
	private String description;
	private String studentNumber;

	public Photo toEntity(Competition competition, User user, String imagePath) {
		return Photo.builder()
			.user(user)
			.competition(competition)
			.title(title)
			.description(description)
			.imagePath(imagePath)
			.studentNumber(studentNumber)
			.build();
	}
}
