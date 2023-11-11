package mjuphotolab.photolabbe.domain.photo.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionPhotoDto;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.repository.PhotoRepository;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

	private final PhotoRepository photoRepository;

	private final UserService userService;

	@Transactional(readOnly = true)
	public PhotoDto getPhoto(Long photoId) {
		Photo photo = photoRepository.findById(photoId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사진을 찾을 수 없습니다."));

		return PhotoDto.from(photo, userService.findByStudentNumber(photo.getStudentNumber()));
	}

	public void deletePhoto(Long photoId) {
		Photo photo = photoRepository.findById(photoId)
				.orElseThrow(() -> new IllegalArgumentException("[Error] 사진을 찾을 수 없습니다."));
		photoRepository.delete(photo);
	}

	@Transactional(readOnly = true)
	public List<BestPhotoDto> findBestPhotos() {
		Pageable pageable = PageRequest.of(0, 4);
		List<Photo> photos = photoRepository.getBestPhotos(pageable);
		return photos.stream()
			.map(photo -> BestPhotoDto.from(photo, getNickname(photo)))
			.collect(Collectors.toList());
	}

	private String getNickname(final Photo photo) {
		return userService.findByStudentNumber(photo.getStudentNumber()).getNickname();
	}
}
