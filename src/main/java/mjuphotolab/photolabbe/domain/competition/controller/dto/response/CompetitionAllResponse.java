package mjuphotolab.photolabbe.domain.competition.controller.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompetitionAllResponse {

	private final int competitionCount;
	private final List<CompetitionDto> competitionDtos;

	@Builder
	private CompetitionAllResponse(final int competitionCount, final List<CompetitionDto> competitionDtos) {
		this.competitionCount = competitionCount;
		this.competitionDtos = competitionDtos;
	}

	public static CompetitionAllResponse of(List<CompetitionDto> competitionDtos) {
		return CompetitionAllResponse.builder()
			.competitionCount(competitionDtos.size())
			.competitionDtos(competitionDtos)
			.build();
	}
}
