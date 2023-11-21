package mjuphotolab.photolabbe.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mjuphotolab.photolabbe.domain.user.entity.SocialType;
import mjuphotolab.photolabbe.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByOauthId(final String oauthId);

	Optional<User> findByEmail(String email);

	Optional<User> findByNickname(String refreshToken);

	Optional<User> findByRefreshToken(String refreshToken);

	Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String id);

	Optional<User> findByStudentNumber(String studentNumber);
}
