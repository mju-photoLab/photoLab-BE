package mjuphotolab.photolabbe.domain.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/")
public class UserPageController {

	private final UserService userService;

	// @GetMapping("/mypage")
	// public UserPageResponse myPage(@LoginUser User user) {
	// 	return userService.findMyPage(user.getId());
	// }
}
