package mjuphotolab.photolabbe.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponse {
	private String code;
	private String message;

	@Builder
	private ExceptionResponse(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	public static ExceptionResponse of(final String code, final String message) {
		return ExceptionResponse.builder()
			.code(code)
			.message(message)
			.build();
	}
}
