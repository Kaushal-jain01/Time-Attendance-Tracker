package com.example.TimesheetManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.UserPrincipal;
import com.example.TimesheetManagement.repository.EmployeeRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee user = userRepo.findByEmail(username);
		
		if(user == null) {
			System.out.println("User Not Found.");
			throw new UsernameNotFoundException("User Not Found.");
		}
		
		return new UserPrincipal(user);
	}

}
