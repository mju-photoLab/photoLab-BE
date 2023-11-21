package mjuphotolab.photolabbe.domain.user.controller.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.AwardPhotoDto;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class UserPageResponse {

	private final String email;
	private final String studentNumber;
	private final String role;
	private final List<AwardPhotoDto> awardPhotoDtos;

	@Builder
	public UserPageResponse(final String email, final String studentNumber, final String role,
		final List<AwardPhotoDto> awardPhotoDtos) {
		this.email = email;
		this.studentNumber = studentNumber;
		this.role = role;
		this.awardPhotoDtos = awardPhotoDtos;
	}

	public static UserPageResponse from(User user, List<AwardPhotoDto> awardPhotoDtos) {
		return UserPageResponse.builder()
			.email(user.getEmail())
			.studentNumber(user.getStudentNumber())
			.role(user.getRole().name())
			.awardPhotoDtos(awardPhotoDtos)
			.build();
	}
}
