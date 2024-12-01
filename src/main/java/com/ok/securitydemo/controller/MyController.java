package com.ok.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/")
	public String defaultMethod() {
		return "this is the default API for everyone";
	}

	@GetMapping("/home")
	public String homeMethod() {
		return "this is home API for everyone";
	}

	@GetMapping("/public/api")
	public String apiMethod() {
		return "this for authenticated public API";
	}

	@GetMapping("/admin/api")
	public String adminMethod() {
		return "this for authenticated admin API";
	}

	@PreAuthorize("hasRole('ROLE_ADMINSP')")
	@GetMapping("/private/api")
	public String privateMethod() {
		return "this for authenticated private admin API";
	}
}
