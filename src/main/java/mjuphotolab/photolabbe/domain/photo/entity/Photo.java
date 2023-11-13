package mjuphotolab.photolabbe.domain.photo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mjuphotolab.photolabbe.common.BaseEntity;
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

	private int likeCount;

	public void updateLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Builder
	private Photo(final User user, final Exhibition exhibition, final Competition competition,
		final String title, final String description,
		final String imagePath, final String studentNumber, final int likeCount) {
		this.user = user;
		this.exhibition = exhibition;
		this.competition = competition;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.studentNumber = studentNumber;
		this.likeCount = likeCount;
	}
}
