package com.spring_security.TestSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		
		http.csrf((auth) -> auth.disable());
				
		return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails user = User.withUsername("user")
//            .password(encoder.encode("1234"))
//            .roles("USER")
//            .build();
//        UserDetails admin = User.withUsername("admin")
//            .password(encoder.encode("admin1234"))
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//    
    
}