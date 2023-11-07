package mjuphotolab.photolabbe.domain.photo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public Photo getPhoto(Long photoId) {
        Optional<Photo> photo = photoRepository.findById(photoId);

        return photo.orElseThrow(() -> new IllegalArgumentException("[Error] 사진이 존재하지 않습니다."));
    }

    public void uploadPhoto(String title, String description, String imagePath) {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setImagePath(imagePath);
    }
}
