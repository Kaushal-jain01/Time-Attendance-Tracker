package com.example.TimesheetManagement.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimesheetManagement.model.AttendanceDto;
import com.example.TimesheetManagement.model.Timesheet;
import com.example.TimesheetManagement.service.TimesheetService;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {
	@Autowired
	private TimesheetService timesheetService;
	
	@GetMapping("/getMessage")
	public ResponseEntity<String> getMsg() {
		String message = "Welcome to Timesheet Page!";
		return ResponseEntity.ok(message);
	}

//	@PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'EMPLOYEE')")
	@PreAuthorize("authentication.principal.id == #eid")
	@PostMapping("/clock-in/{eid}")
	public ResponseEntity<Timesheet> clockIn(@PathVariable Integer eid, @RequestParam LocalDate date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime clockInTime) {
		try {
			Timesheet timesheet = timesheetService.clockIn(eid, date, clockInTime);
			return ResponseEntity.ok(timesheet);
		} 
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
//	@PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'EMPLOYEE')")
	@PreAuthorize("authentication.principal.id == #eid")
	@PostMapping("/clock-out/{eid}")
	public ResponseEntity<Timesheet> clockOut(@PathVariable Integer eid, @RequestParam LocalDate date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime clockOutTime) {

		try {
			Timesheet timesheet = timesheetService.clockOut(eid, date, clockOutTime);
			return ResponseEntity.ok(timesheet);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'EMPLOYEE')")
	@GetMapping("/hoursWorked/{eid}")
	public ResponseEntity<Integer> getHoursWorked(@PathVariable Integer eid, @RequestParam LocalDate date) {
		try {
			int totalHours = timesheetService.getHoursWorked(eid, date);
			return ResponseEntity.ok(totalHours);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'EMPLOYEE')")
//	@PreAuthorize("authentication.principal.id == #empId")
//	@GetMapping("/get-payroll/{empId}/month/{monthId}")
//	public ResponseEntity<Integer> getPayrollById(@PathVariable Integer empId, @PathVariable Integer monthId){
//		try {
//			System.out.println("Reached!!!");
//			int amount = timesheetService.calculatePayroll(empId, monthId);
//			System.out.println("Amount" + amount);
//			return ResponseEntity.ok(amount);
//		}
//		catch(Exception e) {
//			return ResponseEntity.badRequest().build();
//		}
//	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER', 'EMPLOYEE')")
	@GetMapping("/attendance-report/{empId}/month/{monthId}")
	public ResponseEntity<AttendanceDto> getAttendanceReport(@PathVariable Integer empId, @PathVariable Integer monthId){
		try {
			System.out.println("reached attendance");
			AttendanceDto report = timesheetService.getAttendanceReport(empId, monthId);
			return ResponseEntity.ok(report);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
