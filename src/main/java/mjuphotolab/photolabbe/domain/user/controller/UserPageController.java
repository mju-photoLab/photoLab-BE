package mjuphotolab.photolabbe.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.meta.LoginUser;
import mjuphotolab.photolabbe.domain.user.controller.dto.request.UpdateUserRequest;
import mjuphotolab.photolabbe.domain.user.controller.dto.response.UserPageResponse;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/mypage")
public class UserPageController {

	private final UserService userService;

	@GetMapping
	public UserPageResponse myPage(@LoginUser User user) {
		return userService.findMyPage(user.getId());
	}

	@PatchMapping("/changeInfo")
	public void changeInfo(@LoginUser User user, @RequestBody UpdateUserRequest updateUserRequest) {
		userService.updateUser(user.getId(), updateUserRequest);
	}
}
