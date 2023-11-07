package mjuphotolab.photolabbe.domain.photo.controller;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;
import mjuphotolab.photolabbe.domain.photo.service.PhotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    // TODO: S3 사용하여 업로드 구현
    // @PostMapping("/new")
    // public void uploadPhoto(@RequestBody RegisterPhotoDto registerPhotoDto) {
    //     photoService.uploadPhoto(registerPhotoDto);
    // }

    @GetMapping("{photoId}")
    public PhotoDto getPhoto(@PathVariable("photoId") Long photoId) {
        return photoService.getPhoto(photoId);
    }
}
