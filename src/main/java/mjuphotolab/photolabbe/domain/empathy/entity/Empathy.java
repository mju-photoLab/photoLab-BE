package mjuphotolab.photolabbe.domain.empathy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empathy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empathy_id")
	private Long id;
}
