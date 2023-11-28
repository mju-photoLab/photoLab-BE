package mjuphotolab.photolabbe.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.auth.controller.dto.request.UserSignUpRequest;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

	private final UserService userService;

	@PostMapping("/sign-up")
	public void signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
		userService.signUp(userSignUpRequest);
	}
}
