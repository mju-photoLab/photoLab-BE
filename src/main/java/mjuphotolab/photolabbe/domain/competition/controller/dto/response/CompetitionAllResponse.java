package mjuphotolab.photolabbe.domain.competition.controller.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.common.controller.dto.UserProfileDto;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class CompetitionAllResponse {

	private final UserProfileDto userProfileDto;
	private final int competitionCount;
	private final List<CompetitionDto> competitionDtos;

	@Builder
	private CompetitionAllResponse(final UserProfileDto userProfileDto, final int competitionCount, final List<CompetitionDto> competitionDtos) {
		this.userProfileDto = userProfileDto;
		this.competitionCount = competitionCount;
		this.competitionDtos = competitionDtos;
	}


	public static CompetitionAllResponse of(User user, List<CompetitionDto> competitionDtos) {
		return CompetitionAllResponse.builder()
			.userProfileDto(UserProfileDto.of(user))
			.competitionCount(competitionDtos.size())
			.competitionDtos(competitionDtos)
			.build();
	}
}
