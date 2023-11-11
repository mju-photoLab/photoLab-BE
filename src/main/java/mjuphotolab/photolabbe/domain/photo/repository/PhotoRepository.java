package mjuphotolab.photolabbe.domain.photo.repository;
import java.util.List;

import mjuphotolab.photolabbe.domain.photo.entity.Photo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("SELECT p FROM Photo p ORDER BY p.likeCount DESC")
	List<Photo> getBestPhotos(Pageable pageable);
}
