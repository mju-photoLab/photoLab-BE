package mjuphotolab.photolabbe.domain.competition.controller.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;

@Getter
public class DetailCompetitionResponse {
	private final String title;
	private final String content;
	private final List<PhotoDto> photoDtos;

	@Builder
	private DetailCompetitionResponse(final String title, final String content, final List<PhotoDto> photoDtos) {
		this.title = title;
		this.content = content;
		this.photoDtos = photoDtos;
	}

	public static DetailCompetitionResponse from(Competition competition, List<PhotoDto> photoDtos) {
		return DetailCompetitionResponse.builder()
			.title(competition.getTitle())
			.content(competition.getContent())
			.photoDtos(photoDtos)
			.build();
	}
}
