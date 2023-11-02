package mjuphotolab.photolabbe.domain.competition.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;

@Getter
public class CompetitionAllResponse {
	private Long id;
	private String title;
	private String content;
	private int awards;

	@Builder
	private CompetitionAllResponse(final Long id, final String title, final String content, final int awards) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.awards = awards;
	}

	public static CompetitionAllResponse of(final Competition competition) {
		return CompetitionAllResponse.builder()
			.id(competition.getId())
			.title(competition.getTitle())
			.content(competition.getContent())
			.awards(competition.getAwards())
			.build();
	}
}
