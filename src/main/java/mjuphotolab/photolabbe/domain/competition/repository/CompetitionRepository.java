package mjuphotolab.photolabbe.domain.competition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mjuphotolab.photolabbe.domain.competition.entity.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
	List<Competition> findAllBy();
	Optional<Competition> findById(Long competitionId);
}
