package mjuphotolab.photolabbe.domain.user.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String oauthId;

	private String nickname;

	private String password;

	private String email;

	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	private String socialId;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String refreshToken;

	public void authorizeUser() {
		this.role = Role.USER;
	}

	public void passwordEncode(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}

	public void updateRefreshToken(String updateRefreshToken) {
		this.refreshToken = updateRefreshToken;
	}

	public void update(final UpdateUserRequest request) {
		this.nickname = request.getNickname() != null ? request.getNickname() : this.nickname;
		this.email = request.getEmail() != null ? request.getEmail() : this.email;
		this.password = request.getPassword() != null ? request.getPassword() : this.password;
	}
}

