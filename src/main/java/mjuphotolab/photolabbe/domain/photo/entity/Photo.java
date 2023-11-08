package mjuphotolab.photolabbe.domain.photo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mjuphotolab.photolabbe.common.BaseEntity;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.empathy.entity.Empathy;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photo")
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

	@OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
	private List<Empathy> empathies = new ArrayList<>();

	@Builder
	private Photo(final User user, final Exhibition exhibition, final Competition competition,
		final String title, final String description,
		final String imagePath, final String studentNumber) {
		this.user = user;
		this.exhibition = exhibition;
		this.competition = competition;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.studentNumber = studentNumber;
	}
}
