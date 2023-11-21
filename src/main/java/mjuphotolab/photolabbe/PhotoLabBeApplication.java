package mjuphotolab.photolabbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PhotoLabBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoLabBeApplication.class, args);
	}

}
