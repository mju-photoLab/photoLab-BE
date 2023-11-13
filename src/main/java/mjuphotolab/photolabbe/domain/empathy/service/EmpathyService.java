package mjuphotolab.photolabbe.domain.empathy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.empathy.controller.dto.request.EmpathyDto;
import mjuphotolab.photolabbe.domain.empathy.entity.Empathy;
import mjuphotolab.photolabbe.domain.empathy.repository.EmpathyRepository;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.repository.PhotoRepository;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EmpathyService {

	private final UserRepository userRepository;
	private final PhotoRepository photoRepository;
	private final EmpathyRepository empathyRepository;

	public void insert(EmpathyDto empathyDto, Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
		Photo photo = photoRepository.findById(empathyDto.getPhotoId())
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사진을 찾을 수 없습니다."));

		if (empathyRepository.findByUserAndPhoto(user, photo).isPresent()) {
			throw new IllegalArgumentException("[Error] 이미 좋아요가 눌려있는 상태입니다.");
		}

		Empathy empathy = Empathy.builder()
			.photo(photo)
			.user(user)
			.build();

		int likeCount = photo.getLikeCount();
		empathyRepository.save(empathy);
		photo.updateLikeCount(++likeCount);
	}

	public void delete(EmpathyDto empathyDto, Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
		Photo photo = photoRepository.findById(empathyDto.getPhotoId())
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사진을 찾을 수 없습니다."));

		Empathy empathy = empathyRepository.findByUserAndPhoto(user, photo)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 좋아요를 찾을 수 없습니다."));

		int likeCount = photo.getLikeCount();
		empathyRepository.delete(empathy);
		photo.updateLikeCount(--likeCount);
	}

	public boolean isLiked(User user, Photo photo) {
		return empathyRepository.findByUserAndPhoto(user, photo).isPresent();
	}
}
