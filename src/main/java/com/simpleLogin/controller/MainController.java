package com.simpleLogin.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.simpleLogin.dto.MemberFormDto;
import com.simpleLogin.entity.Member;
import com.simpleLogin.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MemberService mService;
	private final PasswordEncoder pwdEncoder;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/members/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/members/login/error")
	public String loginError(Model model) {
		model.addAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		return "loginForm";
	}
	
	@GetMapping("/members/signup")
	public String signupForm(Model model) {
		MemberFormDto dto = new MemberFormDto();
		model.addAttribute("memberFormDto", dto);
		return "signupForm";
	}
	
	@PostMapping("/members/signup")
	public String test(@Valid MemberFormDto dto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return "signupForm";
		
		try {
			Member member = Member.createMember(dto, this.pwdEncoder);
			mService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "redirect:/members/login";
		}
		
		return "redirect:/";
	}
}
