package mjuphotolab.photolabbe.domain.user.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjuphotolab.photolabbe.auth.controller.dto.request.UserSignUpRequest;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.AwardPhotoDto;
import mjuphotolab.photolabbe.domain.user.controller.dto.response.UserPageResponse;
import mjuphotolab.photolabbe.domain.user.controller.dto.response.UserProfileDto;
import mjuphotolab.photolabbe.domain.user.entity.Role;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.repository.UserRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	// private final ExhibitionService exhibitionService;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(UserSignUpRequest userSignUpRequest) {
		if (userRepository.findByEmail(userSignUpRequest.getEmail()).isPresent()) {
			throw new IllegalArgumentException("[Error] 이미 존재하는 이메일입니다.");
		}

		User user = User.builder()
			.email(userSignUpRequest.getEmail())
			.password(userSignUpRequest.getPassword())
			.nickname(userSignUpRequest.getNickname())
			.studentNumber(userSignUpRequest.getStudentNumber())
			.role(Role.USER)
			.build();

		user.passwordEncode(passwordEncoder);
		userRepository.save(user);
		log.info("회원 저장 성공");
	}

	public User findUser(final Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
	}

	public UserProfileDto findUserProfile(User user) {
		return UserProfileDto.of(user);
	}

	public User findByStudentNumber(final String studentNumber) {
		return userRepository.findByStudentNumber(studentNumber)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
	}

	// TODO 마이페이지 찾는 로직
	// public UserPageResponse findMyPage(Long userId) {
	// 	User user = findUser(userId);
	// 	List<AwardPhotoDto> awardPhotoDtos = exhibitionService.findByStudentNUmber(user.getStudentNumber());
	//
	// 	return UserPageResponse.from(user, awardPhotoDtos);
	// }

	@Transactional
	public Long changeRole(final Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
		if (!isAdmin(user)) {
			user.updateRole(Role.ADMIN);
			return user.getId();
		}
		user.updateRole(Role.USER);
		return user.getId();
	}

	private Boolean isAdmin(User user) {
		return user.getRole().name().equals("ADMIN");
	}

}
