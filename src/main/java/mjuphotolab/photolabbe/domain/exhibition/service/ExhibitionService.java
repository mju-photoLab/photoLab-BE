package mjuphotolab.photolabbe.domain.exhibition.service;

import static mjuphotolab.photolabbe.aws.PhotoDomainType.*;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.aws.service.AwsS3Service;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionToExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.RegisterExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.UpdateExhibitionRequest;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.DetailExhibitionResponse;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionAllResponse;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.response.ExhibitionDto;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.exhibition.repository.ExhibitionRepository;
import mjuphotolab.photolabbe.domain.photo.controller.dto.request.InitExhibitionPhotoDto;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.AwardPhotoDto;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.ExhibitionPhotoDto;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.service.PhotoService;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExhibitionService {

	private final ExhibitionRepository exhibitionRepository;
	private final PhotoService photoService;
	private final UserService userService;
	private final AwsS3Service awsS3Service;

	public void registerExhibition(List<MultipartFile> multipartFiles,
		RegisterExhibitionRequest registerExhibitionRequest, Long userId) {
		User user = userService.findUser(userId);
		Exhibition exhibition = registerExhibitionRequest.toEntity(user);
		exhibitionRepository.save(exhibition);

		uploadPhotos(multipartFiles, exhibition, user);
	}

	@Transactional(readOnly = true)
	public ExhibitionAllResponse findExhibitions() {
		List<Exhibition> exhibitions = exhibitionRepository.findAll();
		List<ExhibitionDto> exhibitionDtos = exhibitions.stream()
			.map(ExhibitionDto::of)
			.toList();
		return ExhibitionAllResponse.of(exhibitionDtos);
	}

	@Transactional(readOnly = true)
	public DetailExhibitionResponse findExhibition(Long exhibitionId, Long userId) {
		Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 전시를 찾을 수 없습니다."));
		User user = userService.findUser(userId);

		List<ExhibitionPhotoDto> exhibitionPhotoDtos = exhibition.getPhotos().stream()
			.map(photo -> ExhibitionPhotoDto.from(photo, user))
			.toList();
		return DetailExhibitionResponse.from(exhibition, exhibitionPhotoDtos);
	}

	public void updateExhibition(Long exhibitionId, UpdateExhibitionRequest updateExhibitionRequest) {
		Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 전시를 찾을 수 없습니다."));

		exhibition.update(updateExhibitionRequest);
	}

	public void fromCompetition(CompetitionToExhibitionRequest request, List<Photo> photos, User user) {
		Exhibition exhibition = request.toEntity(photos, user);
		exhibitionRepository.save(exhibition);
	}

	public List<AwardPhotoDto> findAwardPhotosBy(String studentNumber) {
		return photoService.findAwardPhotosBy(studentNumber);
	}

	private void uploadPhotos(final List<MultipartFile> multipartFiles, final Exhibition exhibition,
		final User user) {
		List<String> uploadFileUrls = awsS3Service.uploadImages(EXHIBITION.name(), multipartFiles);

		List<Photo> photos = uploadFileUrls.stream()
			.map(url -> InitExhibitionPhotoDto.toEntity(exhibition, user, url))
			.collect(Collectors.toList());

		photoService.registerAll(photos);
	}
}
