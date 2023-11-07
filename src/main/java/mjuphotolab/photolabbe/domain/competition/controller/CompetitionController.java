package mjuphotolab.photolabbe.domain.competition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.RegisterCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.service.CompetitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@Slf4j
@RequestMapping("api")
@RequiredArgsConstructor
public class CompetitionController {

	private final CompetitionService competitionService;

	@PostMapping("/competitions/new")
	public CompetitionResponse registerCompetition(@Valid @RequestBody RegisterCompetitionRequest registerCompetitionRequest,
		@LoginUser User user) {
		return competitionService.register(registerCompetitionRequest, user);
	}

	@GetMapping("/competitions/{competitionId}")
	public CompetitionResponse findCompetition(@PathVariable Long competitionId) {
		return competitionService.findById(competitionId);
	}

	@GetMapping("/competitions")
	public CompetitionAllResponse findCompetitions(@LoginUser User user) {
		return competitionService.findAllCompetitions(user);
	}
}
