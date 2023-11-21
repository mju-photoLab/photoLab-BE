package mjuphotolab.photolabbe.domain.exhibition.repository;

import java.util.List;

import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

	@Query("SELECT e FROM Exhibition e ORDER BY e.createdAt DESC")
	List<Exhibition> findCurrentExhibitions(Pageable pageable);
}
