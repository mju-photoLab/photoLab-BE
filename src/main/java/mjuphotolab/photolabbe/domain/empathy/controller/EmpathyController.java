package mjuphotolab.photolabbe.domain.empathy.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.empathy.controller.dto.request.EmpathyDto;
import mjuphotolab.photolabbe.domain.empathy.service.EmpathyService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/empathies")
public class EmpathyController {

	private final EmpathyService empathyService;

	@PostMapping
	public void insert(@RequestBody EmpathyDto empathyDto, @LoginUser User user) {
		empathyService.insert(empathyDto, user.getId());
	}

	@DeleteMapping
	public void delete(@RequestBody EmpathyDto empathyDto, @LoginUser User user) {
		empathyService.delete(empathyDto, user.getId());
	}

}
