package mjuphotolab.photolabbe.domain.photo.repository;

import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
