package mjuphotolab.photolabbe.domain.competition.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.CompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionAllResponse;
import mjuphotolab.photolabbe.domain.competition.controller.dto.response.CompetitionResponse;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.competition.repository.CompetitionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionService {

	private final CompetitionRepository competitionRepository;

	public CompetitionResponse register(CompetitionRequest competitionRequest) {
		Competition competition = competitionRequest.toEntity();
		competitionRepository.save(competition);
		return CompetitionResponse.of(competition);
	}

	@Transactional(readOnly = true)
	public List<CompetitionAllResponse> findAllCompetitions() {
		List<Competition> competitions = competitionRepository.findAllBy();
		return competitions.stream()
			.map(CompetitionAllResponse::of)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CompetitionResponse findById(Long competitionId) {
		Competition competition = competitionRepository.findById(competitionId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 해당 공모전을 찾을 수 없습니다."));
		return CompetitionResponse.of(competition);
	}
}
