package mjuphotolab.photolabbe.domain.exhibition.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CurrentExhibitionDto {

	private Long exhibitionId;
	private String imagePath;

	@Builder
	private CurrentExhibitionDto(final Long exhibitionId, final String imagePath) {
		this.exhibitionId = exhibitionId;
		this.imagePath = imagePath;
	}

	public static CurrentExhibitionDto from(Long exhibitionId, String imagePath) {
		return CurrentExhibitionDto.builder()
			.exhibitionId(exhibitionId)
			.imagePath(imagePath)
			.build();
	}
}