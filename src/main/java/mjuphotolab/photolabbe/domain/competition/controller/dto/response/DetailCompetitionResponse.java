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
	private final List<PhotoDto> photos;

	@Builder
	private DetailCompetitionResponse(final String title, final String content, final List<PhotoDto> photos) {
		this.title = title;
		this.content = content;
		this.photos = photos;
	}

	public static DetailCompetitionResponse from(Competition competition, List<PhotoDto> photos) {
		return DetailCompetitionResponse.builder()
			.title(competition.getTitle())
			.content(competition.getContent())
			.photos(photos)
			.build();
	}
}
