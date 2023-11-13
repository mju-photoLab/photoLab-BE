package mjuphotolab.photolabbe.domain.exhibition.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;

import java.time.LocalDateTime;

@Getter
    public class ExhibitionDto {
        private final Long exhibitionId;
        private final String title;
        private final String content;
        private final LocalDateTime createdAt;

        @Builder
    private ExhibitionDto(Long exhibitionId, String title, String content, LocalDateTime createdAt) {
        this.exhibitionId = exhibitionId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static ExhibitionDto of(Exhibition exhibition) {
        return ExhibitionDto.builder()
                .exhibitionId(exhibition.getId())
                .title(exhibition.getTitle())
                .content(exhibition.getContent())
                .createdAt(exhibition.getCreatedAt())
                .build();
    }
}
