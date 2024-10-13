package com.example.TimesheetManagement.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.LoginRequest;
import com.example.TimesheetManagement.model.UserIdRoleDto;
import com.example.TimesheetManagement.model.UserPrincipal;
import com.example.TimesheetManagement.service.AuthenticationService;
import com.example.TimesheetManagement.service.EmployeeService;
import com.example.TimesheetManagement.service.MyUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeController {


	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AuthenticationService authenticatonService;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'EMPLOYEE')")
	@GetMapping("/getId/{username}")
	public ResponseEntity<UserIdRoleDto> getId(@PathVariable String username){
		
		Optional<UserIdRoleDto> employee = employeeService.getEmployeeByUsername(username);
		if(employee.isPresent())
			return ResponseEntity.ok(employee.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> emps = employeeService.getEmployees();
		List<Employee> filteredEmps = emps.stream()
			    .filter(emp -> "EMPLOYEE".equals(emp.getRole()) || "MANAGER".equals(emp.getRole()))
			    .collect(Collectors.toList());
		
		return ResponseEntity.ok(filteredEmps);	
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Employee> register(@RequestBody Employee emp){
		Employee savedEmployee = authenticatonService.register(emp);
		return ResponseEntity.ok(savedEmployee);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER') or authentication.principal.id == #id")
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
		
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if(employee.isPresent())
			return ResponseEntity.ok(employee.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/all-employees/{mId}")
	public ResponseEntity<Object> getAllEmployees(@PathVariable Integer mId){
		
		Optional<List<Employee>> employees = employeeService.getEmployeesByMgrId(mId);
		if(employees.isPresent())
			return ResponseEntity.ok(employees.get());
		
		return ResponseEntity.notFound().build();
	}
}
