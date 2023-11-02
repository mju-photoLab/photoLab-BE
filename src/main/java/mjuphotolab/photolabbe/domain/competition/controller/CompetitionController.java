package mjuphotolab.photolabbe.domain.competition.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.service.CompetitionService;

@RestController
@Slf4j
@RequestMapping("api")
@RequiredArgsConstructor
public class CompetitionController {

	private final CompetitionService competitionService;

	@PostMapping("/competitions/new")
	public CompetitionResponse registerCompetition(@Valid @RequestBody CompetitionRequest competitionRequest) {
		return competitionService.register(competitionRequest);
	}

	@GetMapping("/competitions/{competitionId}")
	public CompetitionResponse findCompetition(@PathVariable Long competitionId) {
		return competitionService.findById(competitionId);
	}

	@GetMapping("/competitions")
	public List<CompetitionAllResponse> findCompetitions() {
		return competitionService.findAllCompetitions();
	}
}
