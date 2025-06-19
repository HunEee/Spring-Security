package com.spring_security.TestSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/loginP")
	public String loginP() {
		return "login1";
	}
	
}
