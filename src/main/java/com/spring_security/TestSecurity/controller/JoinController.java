package com.spring_security.TestSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring_security.TestSecurity.dto.JoinDTO;
import com.spring_security.TestSecurity.service.JoinService;

@Controller
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	

	@GetMapping("/join")
	public String joinP() {
		System.out.println("JoinController: JOIN PAGE 이동");
		return "join";
	}
	
	@PostMapping("/joinProc")
	public String joinProcess(JoinDTO joinDTO) {
		
		System.out.println("JoinController: POST -> "+joinDTO.getUsername());
		joinService.joinProcess(joinDTO);
		System.out.println("JoinController: redirect ");
		return "redirect:/login";
	}
	
	
}
