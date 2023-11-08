package mjuphotolab.photolabbe.domain.competition.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.RegisterCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionDto;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.competition.repository.CompetitionRepository;
import mjuphotolab.photolabbe.domain.photo.controller.dto.response.PhotoDto;
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

	public void register(RegisterCompetitionRequest registerCompetitionRequest, Long userId) {
		User user = userService.findUser(userId);
		Competition competition = registerCompetitionRequest.toEntity(user);

		competitionRepository.save(competition);

		registerCompetitionRequest.getCompetitionPhotoDtos().stream()
			.map(competitionPhotoDto -> competitionPhotoDto.toEntity(competition, user))
			.forEach(photoRepository::save);
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
	public CompetitionResponse findCompetition(Long competitionId, Long userId) {
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));
		User user = userService.findUser(userId);

		List<PhotoDto> photoDtos = competition.getPhotos().stream()
			.map(photo -> PhotoDto.from(photo, user))
			.toList();

		return CompetitionResponse.from(competition, photoDtos);
	}
}
