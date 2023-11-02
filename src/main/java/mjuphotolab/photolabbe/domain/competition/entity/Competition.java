package mjuphotolab.photolabbe.domain.competition.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mjuphotolab.photolabbe.common.BaseEntity;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Competition extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private int awards;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Builder
	private Competition(final String title, final String content, final int awards) {
		this.title = title;
		this.content = content;
		this.awards = awards;
	}
}
