package mjuphotolab.photolabbe.domain.exhibition.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ExhibitionAllResponse {
    private final int exhibitionsCount;
    private final List<ExhibitionDto> exhibitionDtos;

    @Builder
    public ExhibitionAllResponse(final int exhibitionsCount, final List<ExhibitionDto> exhibitionDtos) {
        this.exhibitionsCount = exhibitionsCount;
        this.exhibitionDtos = exhibitionDtos;
    }

    public static ExhibitionAllResponse of(List<ExhibitionDto> exhibitionDtos) {
        return ExhibitionAllResponse.builder()
                .exhibitionsCount(exhibitionDtos.size())
                .exhibitionDtos(exhibitionDtos)
                .build();
    }
}
