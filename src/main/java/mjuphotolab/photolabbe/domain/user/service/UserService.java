package mjuphotolab.photolabbe.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjuphotolab.photolabbe.domain.user.controller.dto.request.UserSignUpDto;
import mjuphotolab.photolabbe.domain.user.entity.Role;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.repository.UserRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void signUp(UserSignUpDto userSignUpDto) {
		if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
			throw new IllegalArgumentException("[Error] 이미 존재하는 이메일입니다.");
		}

		User user = User.builder()
			.email(userSignUpDto.getEmail())
			.password(userSignUpDto.getPassword())
			.nickname(userSignUpDto.getNickname())
			.studentNumber(userSignUpDto.getStudentNumber())
			.role(Role.USER)
			.build();

		user.passwordEncode(passwordEncoder);
		userRepository.save(user);
		log.info("회원 저장 성공");
	}

	public User findUser(final String oauthId) {
		return userRepository.findByOauthId(oauthId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
	}

	public Long setAdmin(final Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
		user.updateRoleTOoAdmin();
		return user.getId();
	}
}
