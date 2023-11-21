package mjuphotolab.photolabbe.domain.photo.repository;
import java.util.List;

import mjuphotolab.photolabbe.domain.photo.entity.Photo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("SELECT p FROM Photo p ORDER BY p.likeCount DESC")
	List<Photo> getBestPhotos(Pageable pageable);

	@Query("SELECT p FROM Photo p WHERE p.competition.id = :competitionId ORDER BY p.likeCount DESC")
	List<Photo> getAwardPhotos(@Param("competitionId") Long competitionId, Pageable pageable);

	@Query("SELECT p FROM Photo p WHERE p.studentNumber = :studentNumber AND p.exhibition IS NOT NULL")
	List<Photo> findAllByStudentNumber(@Param("studentNumber") String studentNumber);
}
