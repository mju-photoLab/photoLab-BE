package mjuphotolab.photolabbe.domain.user.entity;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	GUEST("ROLE_GUEST"),
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN, ROLE_USER");

	private final String roles;

	public List<String> getRoles() {
		return Arrays.asList(roles.split(","));
	}
}
