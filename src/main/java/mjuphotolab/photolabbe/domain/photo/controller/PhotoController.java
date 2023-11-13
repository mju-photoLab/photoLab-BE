package mjuphotolab.photolabbe.domain.photo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.BestPhotoDto;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;
import mjuphotolab.photolabbe.domain.photo.service.PhotoService;



@RestController
@RequestMapping("api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("{photoId}")
    public PhotoDto getPhoto(@PathVariable("photoId") Long photoId) {
        return photoService.findPhoto(photoId);
    }

    @GetMapping("bestPhoto")
    public List<BestPhotoDto> getBestPhotos() {
        return photoService.findBestPhotos();
    }
}
