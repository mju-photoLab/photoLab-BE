package mjuphotolab.photolabbe.domain.exhibition.repository;

import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
}
