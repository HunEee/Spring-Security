package com.spring_security.TestSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final String[] whiteList = {"/WEB-INF/jsp/**","/", "/login","/loginProc","/join", "/joinProc"};
	
	// 필터체인
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
    	//초기 기본 설정
        http.authorizeHttpRequests(
    		(auth) -> auth.requestMatchers(whiteList).permitAll()
                			.requestMatchers("/admin").hasRole("ADMIN")
                			.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                			.anyRequest().authenticated()
        );
		
		http.formLogin(
			(form) -> form.loginPage("/login")
		                	.loginProcessingUrl("/loginProc")
		                	.permitAll()
		                	
        );
		//http basic 방식 -> 헤더에 아이디와 비밀번호를 추가하는 로직 -> formLogin과 같이 사용하지 않음
		//http.httpBasic(Customizer.withDefaults());
		
		
		//csrf 설정 ->  요청을 위조하여 사용자가 원하지 않아도 서버측으로 특정 요청을 강제로 보내는 방식 (회원 정보 변경, 게시글 CRUD를 사용자 모르게 요청)
		//		   ->  앱에서 사용하는 API 서버의 경우 보통 세션을 STATELESS로 관리하기 때문에 스프링 시큐리티 csrf enable 설정을 진행하지 않아도 된다.
		//개발 환경에서만 disable
		//http.csrf((auth) -> auth.disable());
		
		// csrf 설정시 POST 요청으로 로그아웃을 진행해야 하지만 아래 방식을 통해 GET 방식으로 진행 가능
	    http.logout((auth) -> auth.logoutUrl("/logout").logoutSuccessUrl("/"));
		
		
		//다중로그인 설정
		/*
		 * maximumSession(정수) : 하나의 아이디에 대한 다중 로그인 허용 개수
		 * maxSessionPreventsLogin(불린) : 다중 로그인 개수를 초과하였을 경우 처리 방법
		 * 		true : 초과시 새로운 로그인 차단 
		 * 		false : 초과시 기존 세션 하나 삭제
		 */
	    http.sessionManagement(
    		(auth) -> auth.maximumSessions(1)
                		  .maxSessionsPreventsLogin(true)
        );
	    
	    //세션 고정 공격을 보호
		/*
		 * sessionManagement().sessionFixation().none() : 로그인 시 세션 정보 변경 안함
		 * sessionManagement().sessionFixation().newSession() : 로그인 시 세션 새로 생성
		 * sessionManagement().sessionFixation().changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경
		 * 
		 */
	    http.sessionManagement(
    		(auth) -> auth.sessionFixation().changeSessionId()
        );
	    
	    
	    return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    

    // InMemory 방식 
//    @Bean
//    public UserDetailsService userDetailsService() {
//	    UserDetails user1 = User.builder()
//	            .username("user1")
//	            .password(bCryptPasswordEncoder().encode("1234"))
//	            .roles("ADMIN")
//	            .build();
//	
//	    UserDetails user2 = User.builder()
//	            .username("user2")
//	            .password(bCryptPasswordEncoder().encode("1234"))
//	            .roles("USER")
//	            .build();
//	
//	    return new InMemoryUserDetailsManager(user1, user2);
//    }

    
}