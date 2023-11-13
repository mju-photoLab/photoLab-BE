package mjuphotolab.photolabbe.domain.exhibition.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.RegisterExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.UpdateExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionAllResponse;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionDto;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.exhibition.repository.ExhibitionRepository;
import mjuphotolab.photolabbe.domain.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;
    private final UserService userService;

    public Exhibition save(RegisterExhibitionRequest registerExhibitionRequest, Long userId) {
        Exhibition exhibition = registerExhibitionRequest.toEntity(userService.findUser(userId));
        return exhibitionRepository.save(exhibition);
    }

    public ExhibitionAllResponse findExhibitions() {
        List<Exhibition> exhibitions = exhibitionRepository.findAll();
        List<ExhibitionDto> exhibitionDtos = exhibitions.stream()
                .map(ExhibitionDto::of)
                .toList();
        return ExhibitionAllResponse.of(exhibitionDtos);
    }

    public Exhibition findExhibition(long id) {
        return exhibitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    @Transactional
    public Exhibition update(long id, UpdateExhibitionRequest request) {
        Exhibition exhibition = exhibitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        exhibition.update(request.getTitle(), request.getContent());

        return exhibition;
    }
}
