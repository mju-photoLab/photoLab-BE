package mjuphotolab.photolabbe.domain.competition.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.RegisterCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.service.CompetitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@RequestMapping("api/admin/competitions")
@RequiredArgsConstructor
public class AdminCompetitionController {

	private final CompetitionService competitionService;

	@PostMapping("/new")
	public void registerCompetition(@RequestBody RegisterCompetitionRequest registerCompetitionRequest, @LoginUser
		User user) {
		competitionService.register(registerCompetitionRequest, user);
	}
}
