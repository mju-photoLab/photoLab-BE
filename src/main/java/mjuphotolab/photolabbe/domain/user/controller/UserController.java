package mjuphotolab.photolabbe.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.user.controller.dto.request.UserSignUpDto;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/sign-up")
	public void signUp(@RequestBody UserSignUpDto userSignUpDto) {
		userService.signUp(userSignUpDto);
	}
}
