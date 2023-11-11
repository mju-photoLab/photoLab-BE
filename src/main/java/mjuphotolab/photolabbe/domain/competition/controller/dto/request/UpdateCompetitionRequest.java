package mjuphotolab.photolabbe.domain.competition.controller.dto.request;

import lombok.Getter;

@Getter
public class UpdateCompetitionRequest {

	private String title;
	private String content;
	private int awardCount;
}
