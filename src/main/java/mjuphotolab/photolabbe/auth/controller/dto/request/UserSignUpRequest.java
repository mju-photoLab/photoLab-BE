package mjuphotolab.photolabbe.auth.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

	@NotBlank(message = "이름을 입력해주세요.")
	@Size(min = 2, max = 10, message = "이름은 2자에서 10자 사이여야합니다.")
	private String nickname;

	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;

	@Size(min = 8, max = 20, message = "비밀번호는 8자에서 20자 사이여야합니다.")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$", message = "비밀번호는 문자, 숫자, 기호가 1개 이상 포함되어야합니다.")
	private String password;

	@NotBlank(message = "학번을 입력해주세요.")
	private String studentNumber;
}
