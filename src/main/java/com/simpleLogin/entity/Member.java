package com.simpleLogin.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.simpleLogin.constant.Role;
import com.simpleLogin.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto dto, PasswordEncoder pwdEncoder) {
		Member member = new Member();
		member.setName(dto.getName());
		member.setEmail(dto.getEmail());
		member.setPassword(pwdEncoder.encode(dto.getPassword()));
		member.setAddress(dto.getAddress());
		member.setRole(Role.USER);
		
		return member;
	}
}
