package com.simpleLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpleLogin.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findByEmail(String email);

}
