package com.simpleLogin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.simpleLogin.dto.MemberFormDto;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/members/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/members/signup")
	public String signupForm(Model model) {
		MemberFormDto dto = new MemberFormDto();
		model.addAttribute("memberFormDto", dto);
		return "signupForm";
	}
	
	@PostMapping("/members/signup")
	public String test(MemberFormDto dto) {
//		Object obj = model.getAttribute("memberFormDto");
//		Object obj = req.getAttribute("memberFormDto");
//		MemberFormDto dto = (MemberFormDto) obj;
		System.out.println(dto);
		return "main";
	}
}
