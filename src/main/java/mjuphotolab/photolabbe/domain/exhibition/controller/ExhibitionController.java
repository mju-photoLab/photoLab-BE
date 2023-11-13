package mjuphotolab.photolabbe.domain.exhibition.controller;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.LoginUser;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.RegisterExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.UpdateExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionAllResponse;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionResponse;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.exhibition.service.ExhibitionService;
import mjuphotolab.photolabbe.domain.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @PostMapping("/api/exhibitions")
    public ResponseEntity<Exhibition> registerExhibition(@RequestBody RegisterExhibitionRequest request, @LoginUser User user) {
        Exhibition savedExhibition = exhibitionService.save(request, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedExhibition);
    }

    @GetMapping("/api/exhibitions")
    public ResponseEntity<ExhibitionAllResponse> findAllExhibitions() {
        return ResponseEntity.ok()
                .body(exhibitionService.findExhibitions());
    }

    @GetMapping("/api/exhibitions/{exhibitionId}")
    public void findExhibition(@PathVariable long exhibitionId) {
        Exhibition exhibition = exhibitionService.findExhibition(exhibitionId);

    }

    @PutMapping("/api/exhibitions/{exhibitionId}")
    public ResponseEntity<Exhibition> updateExhibition(@PathVariable long id, @RequestBody UpdateExhibitionRequest request) {
        Exhibition updatedExhibition = exhibitionService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedExhibition);
    }

}
