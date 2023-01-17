package com.simpleLogin.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simpleLogin.entity.Member;
import com.simpleLogin.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
//public class MemberService implements UserDetailsService {
public class MemberService {
	private final MemberRepository mRepo;

	public Member saveMember(Member member) {
		return mRepo.save(member);
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Member member = mRepo.findByEmail(email);
//		if (member == null) throw new UsernameNotFoundException(email);
//		return User.builder()
//				.username(member.getEmail())
//				.password(member.getPassword())
//				.roles(member.getRole().toString())
//				.build();
//	}
	
}
