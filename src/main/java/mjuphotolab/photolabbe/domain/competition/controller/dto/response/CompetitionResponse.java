package mjuphotolab.photolabbe.domain.competition.controller.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;

@Getter
public class CompetitionResponse {
	private final String title;
	private final String content;
	private final int awardCount;
	private final List<PhotoDto> photoDtos;

	@Builder
	private CompetitionResponse(final String title, final String content, final int awardCount, final List<PhotoDto> photoDtos) {
		this.title = title;
		this.content = content;
		this.awardCount = awardCount;
		this.photoDtos = photoDtos;
	}

	public static CompetitionResponse from(Competition competition, List<PhotoDto> photoDtos) {
		return CompetitionResponse.builder()
			.title(competition.getTitle())
			.content(competition.getContent())
			.awardCount(competition.getAwardCount())
			.photoDtos(photoDtos)
			.build();
	}
}
