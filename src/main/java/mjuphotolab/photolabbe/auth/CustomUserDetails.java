package mjuphotolab.photolabbe.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import mjuphotolab.photolabbe.domain.user.entity.User;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

	private final User user;
	private Map<String, Object> attribute;

	/* 일반 로그인 생성자 */
	public CustomUserDetails(User user) {
		this.user = user;
	}

	/* OAuth2 로그인 사용자 */
	public CustomUserDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attribute = attributes;
	}

	/* 유저의 권한 목록, 권한 반환*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		user.getRole().getRoles()
			.forEach(role -> collection.add(new SimpleGrantedAuthority(role)));

		return collection;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/* OAuth2User 타입 오버라이딩 */
	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}



}
