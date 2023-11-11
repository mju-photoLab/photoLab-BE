package mjuphotolab.photolabbe.domain.photo.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.BestPhotoDto;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.repository.PhotoRepository;
import mjuphotolab.photolabbe.domain.user.service.UserService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

	private final PhotoRepository photoRepository;
	private final UserService userService;

	@Transactional(readOnly = true)
	public PhotoDto findPhoto(Long photoId) {
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
