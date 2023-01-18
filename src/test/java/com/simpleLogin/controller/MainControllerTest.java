package com.simpleLogin.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.simpleLogin.dto.MemberFormDto;
import com.simpleLogin.entity.Member;
import com.simpleLogin.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MainControllerTest {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private MockMvc mockMvc;
	
	public Member createMember(String email, String password) {
		MemberFormDto member = new MemberFormDto();
		member.setName("test");
		member.setEmail(email);
		member.setPassword(password);
		member.setAddress("testest");
		
		Member _member = Member.createMember(member, this.pwdEncoder);
		return mService.saveMember(_member);
	}
	
	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		String email = "test@email.com";
		String password = "test1234";
		this.createMember(email, password);
		mockMvc.perform(
					formLogin()
						.userParameter("email")
						.loginProcessingUrl("/members/login")
						.user(email)
						.password(password)
				).andExpect(SecurityMockMvcResultMatchers.authenticated());
	}

	@Test
	@DisplayName("로그인 실패 테스트")
	public void loginFailureTest() throws Exception {
		String email = "test@email.com";
		String password = "test1234";
		this.createMember(email, password);
		mockMvc.perform(
					formLogin()
						.userParameter("email")
						.loginProcessingUrl("/members/login")
						.user(email)
						.password("test12345")
				).andExpect(SecurityMockMvcResultMatchers.unauthenticated());
	}
}
