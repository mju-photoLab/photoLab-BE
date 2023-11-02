package mjuphotolab.photolabbe.domain.exhibition.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", updatable = false)
    private String title;

    @Column(name = "content", updatable = false)
    private String content;

    @Column(name = "name", updatable = false)
    private String name;

    @Column(name = "likes", updatable = false)
    private int likes;

    @Column(name = "createdAt", updatable = false)
    private LocalDate createdAt;

    @Builder
    public Exhibition(String title, String content, String name, int likes, LocalDate createdAt) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.likes = likes;
        this.createdAt = createdAt;
    }
}
