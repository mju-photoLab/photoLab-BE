package mjuphotolab.photolabbe.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpDto {

	private String email;
	private String password;
	private String nickname;
	private String studentNumber;
}
