package mjuphotolab.photolabbe.domain.competition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjuphotolab.photolabbe.common.meta.LoginUser;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.DetailCompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.service.CompetitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@Slf4j
@RequestMapping("api/competitions")
@RequiredArgsConstructor
public class CompetitionController {

	private final CompetitionService competitionService;

	@GetMapping("/{competitionId}")
	public DetailCompetitionResponse findCompetition(@PathVariable Long competitionId, @LoginUser User user) {
		return competitionService.findCompetition(competitionId, user.getId());
	}

	@GetMapping
	public CompetitionAllResponse findCompetitions() {
		return competitionService.findAllCompetitions();
	}

}
