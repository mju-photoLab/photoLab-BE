package mjuphotolab.photolabbe.domain.competition.controller.dto.request;

import lombok.Getter;

@Getter
public class RegisterPhotoDto {
	private String files;
	private String title;
	private String description;
	private String studentNumber;
}
