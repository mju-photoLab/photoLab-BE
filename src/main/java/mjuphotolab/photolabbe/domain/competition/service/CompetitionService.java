package mjuphotolab.photolabbe.domain.competition.service;

import static mjuphotolab.photolabbe.aws.PhotoDomainType.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.aws.service.AwsS3Service;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionToExhibitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.RegisterCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.UpdateCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionDto;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.DetailCompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.competition.repository.CompetitionRepository;
import mjuphotolab.photolabbe.domain.empathy.service.EmpathyService;
import mjuphotolab.photolabbe.domain.exhibition.service.ExhibitionService;
import mjuphotolab.photolabbe.domain.photo.controller.dto.request.InitCompetitionPhotoDto;
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
	private final EmpathyService empathyService;
	private final ExhibitionService exhibitionService;
	private final PhotoRepository photoRepository;
	private final UserService userService;
	private final AwsS3Service awsS3Service;

	public void registerCompetition(List<MultipartFile> multipartFiles,
		RegisterCompetitionRequest registerCompetitionRequest, Long userId) {
		User user = userService.findUser(userId);
		Competition competition = registerCompetitionRequest.toEntity(user);
		competitionRepository.save(competition);

		uploadPhotos(multipartFiles, competition, user);
	}

	@Transactional(readOnly = true)
	public CompetitionAllResponse findAllCompetitions() {
		List<Competition> competitions = competitionRepository.findAll();
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
			.map(photo -> PhotoDto.from(photo, user, empathyService.isLiked(user, photo)))
			.toList();

		return DetailCompetitionResponse.from(competition, photoDtos);
	}

	public void updateCompetition(Long competitionId, UpdateCompetitionRequest updateCompetitionRequest) {
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));

		competition.update(updateCompetitionRequest);
	}

	public void transformCompetition(Long competitionId, CompetitionToExhibitionRequest request, Long userId) {
		User user = userService.findUser(userId);
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));

		Pageable pageable = PageRequest.of(0, competition.getAwardCount());
		List<Photo> photos = photoRepository.getAwardPhotos(competitionId, pageable);

		exhibitionService.fromCompetition(request, photos, user);
	}

	private void uploadPhotos(final List<MultipartFile> multipartFiles, final Competition competition,
		final User user) {
		List<String> uploadFileUrls = awsS3Service.uploadImages(COMPETITION.name(), multipartFiles);

		List<Photo> photos = uploadFileUrls.stream()
			.map(url -> InitCompetitionPhotoDto.toEntity(competition, user, url))
			.collect(Collectors.toList());
		photoRepository.saveAll(photos);
	}
}
