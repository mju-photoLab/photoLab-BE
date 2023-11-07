package mjuphotolab.photolabbe.domain.photo.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class PhotoDto {

	private final Long photoId;
	private final String title;
	private final String photographer;
 	private final String imagePath;
	private final String description;
 	private final Boolean likeState;
	private final int likeCount;

	@Builder
	private PhotoDto(final Long photoId, final String title, final String photographer, final String imagePath, final String description,
		final Boolean likeState,
		final int likeCount) {
		this.photoId = photoId;
		this.title = title;
		this.photographer = photographer;
		this.imagePath = imagePath;
		this.description = description;
		this.likeState = likeState;
		this.likeCount = likeCount;
	}

	public static PhotoDto from(Photo photo, User user) {
		return PhotoDto.builder()
			.photoId(photo.getId())
			.title(photo.getTitle())
			.photographer(user.getNickname())
			.description(photo.getDescription())
			.imagePath(photo.getImagePath())
			.likeState(false)
			.likeCount(photo.getEmpathies().size())
			.build();
	}
}
