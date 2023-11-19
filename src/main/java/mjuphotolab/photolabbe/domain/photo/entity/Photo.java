package mjuphotolab.photolabbe.domain.photo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mjuphotolab.photolabbe.common.entity.BaseEntity;
import mjuphotolab.photolabbe.domain.photo.controller.dto.request.PhotoCompetitionRequest;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exhibition_id")
	private Exhibition exhibition;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "competition_id")
	private Competition competition;

	private String title;

	private String description;

	private String imagePath;

	private String studentNumber;

	private String photographer;

	private int likeCount;

	@Version
	private Integer version;

	public void updateLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public void updateInfo(PhotoCompetitionRequest photoCompetitionRequest) {
		this.title = photoCompetitionRequest.getTitle() != null ? photoCompetitionRequest.getTitle() : this.title;
		this.description = photoCompetitionRequest.getDescription() != null ? photoCompetitionRequest.getDescription() :
			this.description;
		this.studentNumber =
			photoCompetitionRequest.getStudentNumber() != null ? photoCompetitionRequest.getStudentNumber() :
				this.studentNumber;
		this.photographer =
			photoCompetitionRequest.getPhotographer() != null ? photoCompetitionRequest.getPhotographer() :
				this.photographer;
	}

	public void updateExhibitionPhoto(Exhibition exhibition) {
		this.exhibition = exhibition != null ? exhibition : this.exhibition;
	}

	@Builder
	private Photo(final User user, final Exhibition exhibition, final Competition competition,
		final String title, final String description, final String imagePath, final String studentNumber,
		final String photographer, final int likeCount) {
		this.user = user;
		this.exhibition = exhibition;
		this.competition = competition;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.studentNumber = studentNumber;
		this.photographer = photographer;
		this.likeCount = likeCount;
	}
}
