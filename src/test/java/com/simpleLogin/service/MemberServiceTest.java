package com.simpleLogin.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.simpleLogin.dto.MemberFormDto;
import com.simpleLogin.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

	@Autowired
	MemberService mService;

	@Autowired
	PasswordEncoder pwdEncoder;
	
	public Member createMemeber() {
		MemberFormDto member = new MemberFormDto();
		member.setName("test");
		member.setEmail("test@email.com");
		member.setPassword("test1234");
		member.setAddress("testest");
		
		return Member.createMember(member, pwdEncoder);
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member = createMemeber();
		Member savedMember = mService.saveMember(member);
		
		assertEquals(member, savedMember);
	}
	
	@Test
	@DisplayName("중복 회원가입 테스트")
	public void saveDuplicatedMemberTest() {
		Member m1 = createMemeber();
		Member m2 = createMemeber();
		
		mService.saveMember(m1);
		
		assertThrows(IllegalStateException.class, () -> mService.saveMember(m2));
	}
}
