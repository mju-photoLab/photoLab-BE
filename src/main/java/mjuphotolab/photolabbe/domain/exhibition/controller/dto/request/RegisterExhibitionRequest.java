package mjuphotolab.photolabbe.domain.exhibition.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.user.entity.User;;

@Getter
public class RegisterExhibitionRequest {

	private String exhibitionTitle;
	private String exhibitionContent;

	public Exhibition toEntity(User user) {
		return Exhibition.builder()
			.title(exhibitionTitle)
			.content(exhibitionContent)
			.user(user)
			.build();
	}
}
