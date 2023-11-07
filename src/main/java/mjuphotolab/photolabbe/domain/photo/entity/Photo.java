package mjuphotolab.photolabbe.domain.photo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mjuphotolab.photolabbe.common.BaseEntity;
import mjuphotolab.photolabbe.domain.competition.entity.Competition;
import mjuphotolab.photolabbe.domain.exhibition.entity.Exhibition;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private int likes;
}
