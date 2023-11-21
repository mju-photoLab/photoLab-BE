package mjuphotolab.photolabbe.domain.exhibition.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.meta.LoginUser;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.CurrentExhibitionDto;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.DetailExhibitionResponse;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionAllResponse;
import mjuphotolab.photolabbe.domain.exhibition.service.ExhibitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exhibitions")
public class ExhibitionController {

	private final ExhibitionService exhibitionService;

	@GetMapping
	public ExhibitionAllResponse findAllExhibitions() {
		return exhibitionService.findExhibitions();
	}

	@GetMapping("/{exhibitionId}")
	public DetailExhibitionResponse findExhibition(@PathVariable Long exhibitionId, @LoginUser User user) {
		return exhibitionService.findExhibition(exhibitionId, user.getId());
	}

	@GetMapping("/currentExhibition")
	public List<CurrentExhibitionDto> findCurrentExhibition() {
		return exhibitionService.findCurrentExhibition();
	}
}
