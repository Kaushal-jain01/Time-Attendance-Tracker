package com.example.TimesheetManagement.service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.LoginRequest;
import com.example.TimesheetManagement.model.UserIdRoleDto;
import com.example.TimesheetManagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	

	public Optional<Employee> getEmployeeById(Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee;
	}

	public Employee register(Employee emp) {
		emp.setPassword(encoder.encode(emp.getPassword()));
		return employeeRepository.save(emp);
	}

	public Employee loginUser(String username, String pword) {
	    Employee user = employeeRepository.findByEmail(username);
	    
	    if (user == null) {
	        return null; // or throw an exception
	    }

	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	    
	    if (passwordEncoder.matches(pword, user.getPassword())) {
	        return user; // Password matched
	    } else {
	        return null; // Password did not match
	    }
	}

	public Optional<UserIdRoleDto> getEmployeeByUsername(String username) {
		
		Employee user = employeeRepository.findByEmail(username);
		
		int id = user.getId();
		String role = user.getRole();
		
		return Optional.ofNullable(new UserIdRoleDto(id, role));
	}

	public Optional<List<Employee>> getEmployeesByMgrId(Integer mId) {
		
		List<Employee> emps = employeeRepository.findAllByManagerId(mId);
		return Optional.ofNullable(emps);
	}

	public List<Employee> getEmployees() {
		
		List<Employee> emps = employeeRepository.findAll();
		return emps;
	}

	
}
