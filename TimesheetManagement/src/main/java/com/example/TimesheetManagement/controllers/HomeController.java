package com.example.TimesheetManagement.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
	
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN', 'MANAGER')")
	@GetMapping("/home")
	public String greet() {
		return "Welcome to Home Page";
	}
}
