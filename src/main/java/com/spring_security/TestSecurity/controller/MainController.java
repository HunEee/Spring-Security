package com.spring_security.TestSecurity.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainP(Model model) {
		
		//세션 정보 확인
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//세션 권한 확인
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		//뷰에 주입
		model.addAttribute("id",id);
		model.addAttribute("role",role);
		
		
		return "main";
	}
	
	
}
