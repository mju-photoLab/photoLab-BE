package mjuphotolab.photolabbe.domain.exhibition.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateExhibitionRequest {
    private String title;
    private String content;
}
