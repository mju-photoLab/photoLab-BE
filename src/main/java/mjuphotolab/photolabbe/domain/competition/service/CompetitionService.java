package mjuphotolab.photolabbe.domain.competition.service;

import static mjuphotolab.photolabbe.aws.PhotoDomainType.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.aws.service.AwsS3Service;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionPhotoDto;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.RegisterCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.UpdateCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionDto;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.DetailCompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.competition.repository.CompetitionRepository;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.photo.repository.PhotoRepository;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionService {

	private final CompetitionRepository competitionRepository;
	private final PhotoRepository photoRepository;
	private final UserService userService;
	private final AwsS3Service awsS3Service;

	public void registerCompetition(List<MultipartFile> multipartFiles,
		RegisterCompetitionRequest registerCompetitionRequest, Long userId) {
		User user = userService.findUser(userId);
		Competition competition = registerCompetitionRequest.toEntity(user);
		competitionRepository.save(competition);

		uploadPhotos(multipartFiles, registerCompetitionRequest, competition, user);
	}

	@Transactional(readOnly = true)
	public CompetitionAllResponse findAllCompetitions() {
		List<Competition> competitions = competitionRepository.findAllBy();
		List<CompetitionDto> competitionDtos = competitions.stream()
			.map(CompetitionDto::of)
			.toList();
		return CompetitionAllResponse.of(competitionDtos);
	}

	@Transactional(readOnly = true)
	public DetailCompetitionResponse findCompetition(Long competitionId, Long userId) {
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));

		User user = userService.findUser(userId);

		List<PhotoDto> photoDtos = competition.getPhotos().stream()
			.map(photo -> PhotoDto.from(photo, user))
			.toList();

		return DetailCompetitionResponse.from(competition, photoDtos);
	}

	public void updateCompetition(Long competitionId, UpdateCompetitionRequest updateCompetitionRequest) {
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));

		competition.update(updateCompetitionRequest);
	}

	private void uploadPhotos(final List<MultipartFile> multipartFiles,
		final RegisterCompetitionRequest registerCompetitionRequest, final Competition competition, final User user) {

		List<Photo> photos = new ArrayList<>();

		List<String> uploadFileUrls = awsS3Service.uploadImages(COMPETITION.name(), multipartFiles);
		List<CompetitionPhotoDto> competitionPhotoDtos = registerCompetitionRequest.getCompetitionPhotoDtos();

		int photosSize = uploadFileUrls.size();
		for (int fileIndex = 0; fileIndex < photosSize; fileIndex++) {
			// TODO 순서 맞게 들어가는지 확인
			photos.add(getPhotoEntity(competition, user, competitionPhotoDtos, fileIndex, uploadFileUrls));
		}

		photoRepository.saveAll(photos);
	}

	private Photo getPhotoEntity(final Competition competition, final User user,
		final List<CompetitionPhotoDto> competitionPhotoDtos, final int fileIndex, final List<String> uploadFileUrls) {
		return competitionPhotoDtos.get(fileIndex).toEntity(competition, user, uploadFileUrls.get(fileIndex));
	}
}
