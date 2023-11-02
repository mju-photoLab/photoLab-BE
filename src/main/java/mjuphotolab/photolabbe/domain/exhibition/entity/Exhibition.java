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
    private Long id;
    private String user_id;
    private String title;
    private String content;

    @Builder
    public Exhibition(String user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
    }
}
