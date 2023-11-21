package mjuphotolab.photolabbe.auth.oauth2.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mjuphotolab.photolabbe.auth.oauth2.CustomOAuth2User;
import mjuphotolab.photolabbe.auth.oauth2.OAuthAttributes;
import mjuphotolab.photolabbe.domain.user.entity.SocialType;
import mjuphotolab.photolabbe.domain.user.entity.User;
import mjuphotolab.photolabbe.domain.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UserRepository userRepository;

	private static final String KAKAO = "kakao";

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");

		/**
		 * DefaultOAuth2UserService 객체를 생성하여, loadUser(userRequest)를 통해 DefaultOAuth2User 객체를 생성 후 반환
		 * DefaultOAuth2UserService의 loadUser()는 소셜 로그인 API의 사용자 정보 제공 URI로 요청을 보내서
		 * 사용자 정보를 얻은 후, 이를 통해 DefaultOAuth2User 객체를 생성 후 반환한다.
		 * 결과적으로, OAuth2User는 OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저
		 */
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 카카오만 사용할 때는 사용하지 않음
		SocialType socialType = SocialType.KAKAO;

		String userNameAttributeName = userRequest.getClientRegistration()
			.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // OAuth2 로그인 시 키(PK)가 되는 값
		Map<String, Object> attributes = oAuth2User.getAttributes(); // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들)

		// socialType에 따라 유저 정보를 통해 OAuthAttributes 객체 생성
		OAuthAttributes extractAttributes = OAuthAttributes.of(socialType, userNameAttributeName, attributes);

		User createdUser = getUser(extractAttributes, socialType); // getUser() 메소드로 User 객체 생성 후 반환

		// DefaultOAuth2User를 구현한 CustomOAuth2User 객체를 생성해서 반환
		return new CustomOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().name())),
			attributes,
			extractAttributes.getNameAttributeKey(),
			createdUser.getEmail(),
			createdUser.getRole()
		);
	}

	/**
	 * SocialType과 attributes에 들어있는 소셜 로그인의 식별값 id를 통해 회원을 찾아 반환하는 메소드
	 * 만약 찾은 회원이 있다면, 그대로 반환하고 없다면 saveUser()를 호출하여 회원을 저장한다.
	 */
	private User getUser(OAuthAttributes attributes, SocialType socialType) {
		User findUser = userRepository.findBySocialTypeAndSocialId(socialType,
			attributes.getOauth2UserInfo().getId()).orElse(null);

		if (findUser == null) {
			return saveUser(attributes, socialType);
		}
		return findUser;
	}

	/**
	 * OAuthAttributes의 toEntity() 메소드를 통해 빌더로 User 객체 생성 후 반환
	 * 생성된 User 객체를 DB에 저장 : socialType, socialId, email, role 값만 있는 상태
	 */
	private User saveUser(OAuthAttributes attributes, SocialType socialType) {
		User createdUser = attributes.toEntity(socialType, attributes.getOauth2UserInfo());
		return userRepository.save(createdUser);
	}
}
