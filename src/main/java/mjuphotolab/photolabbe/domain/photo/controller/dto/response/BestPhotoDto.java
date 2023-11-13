package mjuphotolab.photolabbe.domain.photo.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;

@Getter
public class BestPhotoDto {

	private final Long photoId;
	private final String title;
	private final String photographer;
	private final String imagePath;

	@Builder
	private BestPhotoDto(final Long photoId, final String title, final String photographer, final String imagePath) {
		this.photoId = photoId;
		this.title = title;
		this.photographer = photographer;
		this.imagePath = imagePath;
	}

	public static BestPhotoDto from(Photo photo, String photographer) {
		return BestPhotoDto.builder()
			.photoId(photo.getId())
			.title(photo.getTitle())
			.photographer(photographer)
			.imagePath(photo.getImagePath())
			.build();
	}
}
