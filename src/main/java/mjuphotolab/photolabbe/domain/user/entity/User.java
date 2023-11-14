package mjuphotolab.photolabbe.domain.user.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
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
import mjuphotolab.photolabbe.common.entity.BaseEntity;
import mjuphotolab.photolabbe.domain.user.controller.dto.request.UpdateUserRequest;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String oauthId;

	private String nickname;

	private String password;

	private String email;

	private String studentNumber;

	private String imageUrl;

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

	public void updateNickname(String updateNickname) {
		this.nickname = updateNickname;
	}

	public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(updatePassword);
	}

	public void updateRole(Role role) {
		this.role = role != null ? role : this.role;
	}

	public void updateRefreshToken(String updateRefreshToken) {
		this.refreshToken = updateRefreshToken;
	}

	public void updateInfo(UpdateUserRequest updateUserRequest) {
		this.nickname = updateUserRequest.getNickname() != null ? updateUserRequest.getNickname() : this.nickname;
		this.password = updateUserRequest.getPassword() != null ? updateUserRequest.getPassword() : this.password;
		this.email = updateUserRequest.getEmail() != null ? updateUserRequest.getEmail() : this.email;
		this.studentNumber =
			updateUserRequest.getStudentNumber() != null ? updateUserRequest.getStudentNumber() : this.studentNumber;
	}
}

