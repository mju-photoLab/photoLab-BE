package mjuphotolab.photolabbe.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import mjuphotolab.photolabbe.common.jwt.filter.JwtAuthenticationProcessingFilter;
import mjuphotolab.photolabbe.common.jwt.service.JwtService;
import mjuphotolab.photolabbe.common.login.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import mjuphotolab.photolabbe.common.login.handler.LoginFailureHandler;
import mjuphotolab.photolabbe.common.login.handler.LoginSuccessHandler;
import mjuphotolab.photolabbe.common.login.service.LoginService;
import mjuphotolab.photolabbe.common.ouath2.handler.OAuth2LoginFailureHandler;
import mjuphotolab.photolabbe.common.ouath2.handler.OAuth2LoginSuccessHandler;
import mjuphotolab.photolabbe.common.ouath2.service.CustomOAuth2UserService;
import mjuphotolab.photolabbe.domain.user.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * Spring Security 6에서 websecurityconfigureradapter가 deprecated가 되고 SecurityFilterChain bean을 생성하도록 변경되었습니다.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final LoginService loginService;
	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final ObjectMapper objectMapper;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final CustomOAuth2UserService customOAuth2UserService;

	private static final String API_PREFIX = "/api";

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.headers(AbstractHttpConfigurer::disable)

			.sessionManagement(sessionManagement ->
				sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			/**
			 * 아래의 경로는 사용자의 권한과 상관없이 접근 가능
			 */

			.authorizeHttpRequests(authorizeHttpRequests ->
				authorizeHttpRequests
					.requestMatchers(
						API_PREFIX + "/auth/**",
						API_PREFIX + "/css/**",
						API_PREFIX + "/images/**",
						API_PREFIX + "//js/**",
						API_PREFIX + "/"
					).permitAll()
					.requestMatchers(API_PREFIX + "/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated())

			.oauth2Login(oauth2Configurer ->
				oauth2Configurer
					.successHandler(oAuth2LoginSuccessHandler)
					.failureHandler(oAuth2LoginFailureHandler)
					.userInfoEndpoint(userInfoEndpointConfig ->
						userInfoEndpointConfig
							.userService(customOAuth2UserService)));

		http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
		http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * AuthenticationManager 설정 후 등록
	 * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
	 * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvider 사용
	 * UserDetailsService는 커스텀 LoginService로 등록
	 * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용(return ProviderManager)
	 */
	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(loginService);
		return new ProviderManager(provider);
	}

	/**
	 * 로그인 성공 시 호출되는 LoginSuccessJWTProviderHandler 빈 등록
	 */
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler(jwtService, userRepository);
	}

	/**
	 * 로그인 실패 시 호출되는 LoginFailureHandler 빈 등록
	 */
	@Bean
	public LoginFailureHandler loginFailureHandler() {
		return new LoginFailureHandler();
	}

	/**
	 * CustomJsonUsernamePasswordAuthenticationFilter 빈 등록
	 * 커스텀 필터를 사용하기 위해 만든 커스텀 필터를 Bean으로 등록
	 * setAuthenticationManager(authenticationManager())로 위에서 등록한 AuthenticationManager(ProviderManager) 설정
	 * 로그인 성공 시 호출할 handler, 실패 시 호출할 handler로 위에서 등록한 handler 설정
	 */
	@Bean
	public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
		CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
			= new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
		customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
		customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
		customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
		return customJsonUsernamePasswordLoginFilter;
	}

	@Bean
	public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
		return new JwtAuthenticationProcessingFilter(jwtService,
			userRepository);
	}
}
