package mjuphotolab.photolabbe.domain.user.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class UserResponse {
	private final String nickname;
	private final String role;

	@Builder
	private UserResponse(final String nickname, final String role) {
		this.nickname = nickname;
		this.role = role;
	}

	public static UserResponse of(User user) {
		return UserResponse.builder()
			.nickname(user.getNickname())
			.role(user.getRole().name())
			.build();
	}
}
