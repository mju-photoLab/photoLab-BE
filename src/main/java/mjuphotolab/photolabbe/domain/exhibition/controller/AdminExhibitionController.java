package mjuphotolab.photolabbe.domain.exhibition.controller;

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
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.RegisterExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.UpdateExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.service.ExhibitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/exhibitions")
public class AdminExhibitionController {

	private final ExhibitionService exhibitionService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/new")
	public void registerExhibition
		(@RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
			@RequestPart RegisterExhibitionRequest registerExhibitionRequest,
			@LoginUser User user) {
		exhibitionService.registerExhibition(multipartFiles, registerExhibitionRequest, user.getId());
	}

	@PatchMapping("/{exhibitionId}/edit")
	public void updateExhibition(@PathVariable Long exhibitionId,
		@RequestBody UpdateExhibitionRequest updateExhibitionRequest) {
		exhibitionService.updateExhibition(exhibitionId, updateExhibitionRequest);
	}
}
