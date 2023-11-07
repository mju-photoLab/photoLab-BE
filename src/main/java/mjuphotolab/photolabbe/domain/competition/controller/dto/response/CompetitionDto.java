package mjuphotolab.photolabbe.domain.competition.controller.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;

@Getter
public class CompetitionDto {

	private final Long competitionId;
	private final String title;
	private final String content;
	private final LocalDateTime createdAt;

	@Builder
	private CompetitionDto(final Long competitionId, final String title, final String content,
		final LocalDateTime createdAt) {
		this.competitionId = competitionId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public static CompetitionDto of (final Competition competition) {
		return CompetitionDto.builder()
			.competitionId(competition.getId())
			.title(competition.getTitle())
			.content(competition.getContent())
			.createdAt(competition.getCreatedAt())
			.build();
	}
}
