package mjuphotolab.photolabbe.domain.competition.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mjuphotolab.photolabbe.common.BaseEntity;
import mjuphotolab.photolabbe.domain.competition.controller.dto.request.UpdateCompetitionRequest;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Competition extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "competition_id")
	private Long id;

	private String title;
	private String content;
	private int awardCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private List<Photo> photos = new ArrayList<>();

	@Builder
	private Competition(final String title, final String content, final int awardCount, final User user, final List<Photo> photos) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.awardCount = awardCount;
		this.photos = photos;
	}

	public void update(UpdateCompetitionRequest updateCompetitionRequest) {
		this.title = updateCompetitionRequest.getTitle() != null ? updateCompetitionRequest.getTitle() : this.title;
		this.content = updateCompetitionRequest.getContent() != null ? updateCompetitionRequest.getContent() : this.content;
		this.awardCount = updateCompetitionRequest.getAwardCount();
	}
}
