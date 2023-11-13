package mjuphotolab.photolabbe.domain.exhibition.repository;

import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    List<Exhibition> findAllBy();

    Optional<Exhibition> findById(Long exhibitionId);
}
