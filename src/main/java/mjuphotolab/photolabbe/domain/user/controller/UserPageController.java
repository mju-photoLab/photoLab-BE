package mjuphotolab.photolabbe.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.user.controller.dto.request.UpdateUserRequest;
import mjuphotolab.photolabbe.domain.user.controller.dto.response.UserPageResponse;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/users/mypage")
public class UserPageController {

	private final UserService userService;

	// @GetMapping
	// @ResponseBody
	// public UserPageResponse myPage(@LoginUser User user) {
	// 	return userService.findMyPage(user.getId());
	// }

	@PatchMapping("/changeInfo")
	public String changeInfo(@LoginUser User user, @RequestBody UpdateUserRequest updateUserRequest) {
		userService.updateUser(user.getId(), updateUserRequest);
		return "redirect:/api/users/mypage";
	}
}
