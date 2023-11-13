package mjuphotolab.photolabbe.domain.exhibition.controller.dto.response;

import lombok.Builder;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;

import java.util.List;

@Builder
public class ExhibitionResponse {
    private final String title;
    private final String content;
    private final List<PhotoDto> photoDtos;

    @Builder
    public ExhibitionResponse(String title, String content, List<PhotoDto> photoDtos) {
        this.title = title;
        this.content = content;
        this.photoDtos = photoDtos;
    }

    public static ExhibitionResponse from(Exhibition exhibition, List<PhotoDto> photoDtos) {
        return ExhibitionResponse.builder()
                .title(exhibition.getTitle())
                .content(exhibition.getContent())
                .photoDtos(photoDtos)
                .build();
    }
}
