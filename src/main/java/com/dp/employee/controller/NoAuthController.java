package com.dp.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noAuth")
public class NoAuthController {
	
	@GetMapping("/welcome")
	public String sayHi() {
		return "Hello";
	}

}
