package mjuphotolab.photolabbe.domain.photo.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class ExhibitionPhotoDto {

	private final Long photoId;
	private final String title;
	private final String photographer;
	private final String imagePath;

	@Builder
	private ExhibitionPhotoDto(final Long photoId, final String title, final String photographer,
		final String imagePath) {
		this.photoId = photoId;
		this.title = title;
		this.photographer = photographer;
		this.imagePath = imagePath;
	}

	public static ExhibitionPhotoDto from(Photo photo, User user) {
		return ExhibitionPhotoDto.builder()
			.photoId(photo.getId())
			.title(photo.getTitle())
			.photographer(user.getNickname())
			.imagePath(photo.getImagePath())
			.build();
	}
}
