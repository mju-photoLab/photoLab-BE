package mjuphotolab.photolabbe.domain.competition.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.meta.LoginUser;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionToExhibitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.RegisterCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.UpdateCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.service.CompetitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@RequestMapping("api/admin/competitions")
@RequiredArgsConstructor
public class AdminCompetitionController {

	private final CompetitionService competitionService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/new")
	public void registerCompetition(
		@RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
		@RequestPart RegisterCompetitionRequest registerCompetitionRequest,
		@LoginUser User user) {
		competitionService.registerCompetition(multipartFiles, registerCompetitionRequest, user.getId());
	}

	@PatchMapping("/{competitionId}/edit")
	public void changeCompetition(@PathVariable Long competitionId,
		@RequestBody UpdateCompetitionRequest updateCompetitionRequest) {
		competitionService.updateCompetition(competitionId, updateCompetitionRequest);
	}

	@PostMapping("/transform")
	public void transformCompetition(
		@RequestBody CompetitionToExhibitionRequest competitionToExhibitionRequest,
		@LoginUser User user) {
		competitionService.transformCompetition(competitionToExhibitionRequest, user.getId());
	}
}
