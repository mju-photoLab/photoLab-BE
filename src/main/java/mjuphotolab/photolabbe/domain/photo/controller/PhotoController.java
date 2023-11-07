package mjuphotolab.photolabbe.domain.photo.controller;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.service.PhotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/photo/{photoId}")
    public Photo getPhoto(@PathVariable("photoId") Long photoId) {
        return photoService.getPhoto(photoId);
    }

    // TODO: S3 사용하여 업로드 구현
    @PostMapping("/photo")
    public void uploadPhoto(@RequestParam(value = "title") String title, @RequestParam(value = "description") String description, @RequestParam(value = "imagePath") String imagePath) {
        photoService.uploadPhoto(title, description, imagePath);
    }
}
