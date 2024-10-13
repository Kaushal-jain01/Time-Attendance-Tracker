package com.example.TimesheetManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimesheetManagement.service.PayrollService;
import com.example.TimesheetManagement.service.TimesheetService;

@RestController
@RequestMapping("/payroll")
public class PayrollController {
	
	@Autowired
	private PayrollService payrollService;
	
	@PreAuthorize("authentication.principal.id == #empId")
	@GetMapping("/{empId}/month/{monthId}")
	public ResponseEntity<Integer> getPayrollById(@PathVariable Integer empId, @PathVariable Integer monthId){
		try {
			System.out.println("Reached!!!");
			int amount = payrollService.calculatePayroll(empId, monthId);
			System.out.println("Amount" + amount);
			return ResponseEntity.ok(amount);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
