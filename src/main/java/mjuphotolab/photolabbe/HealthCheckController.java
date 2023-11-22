package mjuphotolab.photolabbe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/health-check")
public class HealthCheckController {

	@GetMapping
	public Long heathCheck() {
		return System.currentTimeMillis();
	}
}
