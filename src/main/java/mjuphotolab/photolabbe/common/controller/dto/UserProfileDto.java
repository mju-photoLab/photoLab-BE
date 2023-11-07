package mjuphotolab.photolabbe.common.controller.dto;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class UserProfileDto {
	private String imageUrl;
	private String nickname;

	@Builder
	private UserProfileDto(final String imageUrl, final String nickname) {
		this.imageUrl = imageUrl;
		this.nickname = nickname;
	}

	public static UserProfileDto of(User user) {
		return UserProfileDto.builder()
			.imageUrl(user.getImageUrl())
			.nickname(user.getNickname())
			.build();
	}
}
