package mjuphotolab.photolabbe.domain.photo.controller.dto.request;

import lombok.Getter;

@Getter
public class DeletePhotoRequest {

	private String uploadFilePath;
	private String uuidFileName;
}
