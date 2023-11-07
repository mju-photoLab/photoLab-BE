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
import mjuphotolab.photolabbe.domain.user.entity.User;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionService {

	private final CompetitionRepository competitionRepository;

	public CompetitionResponse register(RegisterCompetitionRequest registerCompetitionRequest, User user) {
		Competition competition = registerCompetitionRequest.toEntity(user);
		competitionRepository.save(competition);
		return CompetitionResponse.of(competition);
	}

	@Transactional(readOnly = true)
	public CompetitionAllResponse findAllCompetitions(User user) {
		List<Competition> competitions = competitionRepository.findAllBy();
		List<CompetitionDto> competitionDtos = competitions.stream()
			.map(CompetitionDto::of)
			.toList();
		return CompetitionAllResponse.of(user, competitionDtos);
	}

	@Transactional(readOnly = true)
	public CompetitionResponse findById(Long competitionId) {
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));
		return CompetitionResponse.of(competition);
	}
}
