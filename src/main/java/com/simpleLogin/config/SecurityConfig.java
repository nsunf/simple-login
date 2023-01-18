package com.simpleLogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.simpleLogin.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//	@Autowired
//	MemberService mService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable();
		http.formLogin()
			.loginPage("/members/login")
			.defaultSuccessUrl("/")
//			.usernameParameter("email")
			.failureUrl("/members/login/error")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
			.logoutSuccessUrl("/");

		http.authorizeHttpRequests()
			.mvcMatchers("/", "/members/**").permitAll()
			.anyRequest().authenticated();
		
//		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();
	}

	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
}
