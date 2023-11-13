package mjuphotolab.photolabbe.domain.competition.controller.dto.request;

import java.util.List;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class RegisterCompetitionRequest {

	private String competitionTitle;
	private String competitionContent;
	private int awardCount;

	public Competition toEntity(User user) {
		return Competition.builder()
			.user(user)
			.title(competitionTitle)
			.content(competitionContent)
			.awardCount(awardCount)
			.build();
	}
}
