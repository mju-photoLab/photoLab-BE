package mjuphotolab.photolabbe.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * 학번으로 찾아야할 듯
	 */
	// @GetMapping("/users/awards")
	// public AwardsResponse userAwards(@AuthenticationPrincipal User user) {
	// 	userService.getAwards(user);
	// }

	/**
	 * 초기 어드민 권한 부여를 위함
	 */
	@PostMapping("/first")
	public void firstSet(@LoginUser User user) {
		userService.changeRole(user.getId());
	}

}
