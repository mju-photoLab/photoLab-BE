package mjuphotolab.photolabbe.domain.competition.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;

@Getter
public class CompetitionRequest {

	private String title;
	private String content;
	private int awards;

	public Competition toEntity() {
		return Competition.builder()
			.title(title)
			.content(content)
			.awards(awards)
			.build();
	}
}
