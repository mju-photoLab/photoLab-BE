package mjuphotolab.photolabbe.domain.exhibition.entity;

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
import mjuphotolab.photolabbe.common.entity.BaseEntity;
import mjuphotolab.photolabbe.domain.exhibition.controller.dto.request.UpdateExhibitionRequest;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;
import mjuphotolab.photolabbe.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exhibition extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exhibition_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String title;
	private String content;

	@OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL)
	private List<Photo> photos = new ArrayList<>();

	@Builder
	private Exhibition(User user, String title, String content, List<Photo> photos) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.photos = photos;
	}

	public void update(UpdateExhibitionRequest request) {
		this.title = request.getTitle() != null ? request.getTitle() : this.title;
		this.content = request.getContent() != null ? request.getContent() : this.content;
	}
}
