package mjuphotolab.photolabbe.domain.exhibition.controller.dto.request;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class ExhibitionPhotoDto {
    private String files;
    private String title;
    private String description;
    private String imagePath;
    private String studentNumber;

    public Photo toEntity(Exhibition exhibition, User user) {
        return Photo.builder()
                .user(user)
                .exhibition(exhibition)
                .title(title)
                .description(description)
                .imagePath(imagePath)
                .studentNumber(studentNumber)
                .build();
    }
}
