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
	private final int likeCount;
	private final boolean likeState;

	@Builder
	private PhotoDto(final Long photoId, final String title, final String photographer, final String imagePath,
		final int likeCount, final boolean likeState) {
		this.photoId = photoId;
		this.title = title;
		this.photographer = photographer;
		this.imagePath = imagePath;
		this.likeCount = likeCount;
		this.likeState = likeState;
	}

	public static PhotoDto from(Photo photo, User user, boolean likeState) {
		return PhotoDto.builder()
			.photoId(photo.getId())
			.title(photo.getTitle())
			.photographer(user.getNickname())
			.imagePath(photo.getImagePath())
			.likeCount(photo.getLikeCount())
			.likeState(likeState)
			.build();
	}
}
