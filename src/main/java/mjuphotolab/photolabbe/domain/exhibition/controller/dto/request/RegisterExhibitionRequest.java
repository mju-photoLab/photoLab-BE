package mjuphotolab.photolabbe.domain.exhibition.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionDto;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.user.entity.User;

import java.util.List;

@Getter
public class RegisterExhibitionRequest {

    private User user;
    private String title;
    private String content;
    private List<ExhibitionPhotoDto> exhibitionphotoDtos;

    public Exhibition toEntity(User user) {
        return Exhibition.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
