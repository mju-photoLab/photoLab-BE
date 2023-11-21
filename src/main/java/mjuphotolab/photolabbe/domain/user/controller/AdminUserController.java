package mjuphotolab.photolabbe.domain.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.meta.LoginUser;
import mjuphotolab.photolabbe.domain.user.controller.dto.response.UserResponse;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@RestController
@RequestMapping("api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

	private final UserService userService;

	@PatchMapping("/editRole")
	public Long updateRoleToAdmin(@LoginUser User user) {
		return userService.changeRole(user.getId());
	}

	@GetMapping
	public List<UserResponse> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/jwt-test")
	public String jwtTest() {
		return "jwtTest 요청 성공";
	}
}
