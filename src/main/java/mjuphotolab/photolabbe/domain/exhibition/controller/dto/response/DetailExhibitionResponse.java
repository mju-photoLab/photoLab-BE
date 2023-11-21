package mjuphotolab.photolabbe.domain.exhibition.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.ExhibitionPhotoDto;

import java.util.List;

@Getter
public class DetailExhibitionResponse {
	private final String exhibitionTitle;
	private final String exhibitionContent;
	private final List<ExhibitionPhotoDto> photos;

	@Builder
	private DetailExhibitionResponse(final String exhibitionTitle, final String exhibitionContent,
		final List<ExhibitionPhotoDto> photos) {
		this.exhibitionTitle = exhibitionTitle;
		this.exhibitionContent = exhibitionContent;
		this.photos = photos;
	}

	public static DetailExhibitionResponse from(Exhibition exhibition, List<ExhibitionPhotoDto> exhibitionPhotoDtos) {
		return DetailExhibitionResponse.builder()
			.exhibitionTitle(exhibition.getTitle())
			.exhibitionContent(exhibition.getContent())
			.photos(exhibitionPhotoDtos)
			.build();
	}
}
