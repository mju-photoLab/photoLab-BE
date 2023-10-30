package mjuphotolab.photolabbe.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.user.controller.dto.request.UserSignUpDto;
import mjuphotolab.photolabbe.domain.user.entity.Role;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void signUp(UserSignUpDto userSignUpDto) {
		userRepository.findByEmail(userSignUpDto.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("[Error] 이미 존재하는 이메일입니다."));

		userRepository.findByNickname(userSignUpDto.getNickname())
			.orElseThrow(() -> new IllegalArgumentException("[Error] 이미 존재하는 닉네임입니다."));

		User user = User.builder()
			.email(userSignUpDto.getEmail())
			.password(userSignUpDto.getPassword())
			.nickname(userSignUpDto.getNickname())
			.role(Role.USER)
			.build();

		user.passwordEncode(passwordEncoder);
		userRepository.save(user);
	}

	public User findUser(final String oauthId) {
		return userRepository.findByOauthId(oauthId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
	}
}
