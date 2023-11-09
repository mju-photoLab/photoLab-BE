package mjuphotolab.photolabbe.domain.photo.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;

@Getter
public class AwardPhotoDto {

	private final Long photoId;
	private final String imagePath;

	@Builder
	public AwardPhotoDto(final Long photoId, final String imagePath) {
		this.photoId = photoId;
		this.imagePath = imagePath;
	}

	public static AwardPhotoDto of(Photo photo) {
		return AwardPhotoDto.builder()
			.photoId(photo.getId())
			.imagePath(photo.getImagePath())
			.build();
	}
}
