package mjuphotolab.photolabbe.domain.competition.controller.dto.request;

import java.util.List;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class CompetitionToExhibitionRequest {

	private String exhibitionTitle;
	private String exhibitionContent;

	public Exhibition toEntity(List<Photo> photos, User user) {
		return Exhibition.builder()
			.content(exhibitionContent)
			.title(exhibitionTitle)
			.photos(photos)
			.user(user)
			.build();
	}
}
