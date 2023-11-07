package mjuphotolab.photolabbe.domain.empathy.entity;

import jakarta.persistence.*;
import mjuphotolab.photolabbe.domain.photo.entity.Photo;

@Entity
public class Empathy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empathy_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
