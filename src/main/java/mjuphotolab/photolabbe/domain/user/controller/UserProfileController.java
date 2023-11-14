package mjuphotolab.photolabbe.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.meta.LoginUser;
import mjuphotolab.photolabbe.domain.user.controller.dto.response.UserProfileDto;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(("api/users/profile"))
public class UserProfileController {

	private final UserService userService;

	@GetMapping
	public UserProfileDto getProfile(@LoginUser User user) {
		return userService.findUserProfile(user);
	}
}
