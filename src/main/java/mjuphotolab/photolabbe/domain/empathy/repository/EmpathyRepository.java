package mjuphotolab.photolabbe.domain.empathy.repository;

import java.util.Optional;

import mjuphotolab.photolabbe.domain.empathy.entity.Empathy;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpathyRepository extends JpaRepository<Empathy, Long> {

	Optional<Empathy> findByUserAndPhoto(final User user, final Photo photo);
}
