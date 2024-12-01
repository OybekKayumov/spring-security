package com.ok.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/")
	public String defaultMethod() {
		return "this is the default API";
	}

	@GetMapping("/public/api")
	public String apiMethod() {
		return "this for authenticated API";
	}

	@GetMapping("/admin/api")
	public String adminMethod() {
		return "this for admin API";
	}

}
