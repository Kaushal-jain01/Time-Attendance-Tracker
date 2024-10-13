package com.example.TimesheetManagement.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.LoginRequest;
import com.example.TimesheetManagement.service.EmployeeService;
import com.example.TimesheetManagement.service.MyUserDetailsService;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private EmployeeService employeeService;

    @Autowired
    private MyUserDetailsService userDetailsService;
	
//	@PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
//        try {
//
//			Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//            );
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            return ResponseEntity.ok(userDetails); // Return user details upon successful login
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
}
