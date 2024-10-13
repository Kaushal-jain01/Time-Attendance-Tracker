package com.example.TimesheetManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.Payroll;
import com.example.TimesheetManagement.model.Timesheet;
import com.example.TimesheetManagement.repository.EmployeeRepository;
import com.example.TimesheetManagement.repository.MonthRepository;
import com.example.TimesheetManagement.repository.PayrollRepository;
import com.example.TimesheetManagement.repository.TimesheetRepository;

@Service
public class PayrollService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TimesheetRepository timesheetRepository;
	
	@Autowired
	private MonthRepository monthRepository;
	
	@Autowired
	private PayrollRepository payrollRepository;
	
	public Integer calculatePayroll(Integer empId, Integer month) {

		Employee employee = employeeRepository.findById(empId).get();
		int endDay = monthRepository.findTotalDaysById(month).get();
		System.out.println("endDay: " + endDay);
		LocalDate startDate = LocalDate.of(2024, month, 1);
		LocalDate endDate = LocalDate.of(2024, month, endDay);
		List<Timesheet> timesheetList = timesheetRepository.findByEmployeeIdAndDateBetween(empId, startDate, endDate);

		int daysPresent = timesheetList.size();
		System.out.println("daysPresent: " + daysPresent);

		int extraHrsWorked = 0;
		for (Timesheet t : timesheetList) {
			extraHrsWorked += t.getExtraHoursWorked();
		}

		int amount = daysPresent * (2000) + extraHrsWorked * (167);
		System.out.println("amount: " + amount);
		savePayroll(employee, month, amount);
		return amount;
	}
	
	public Payroll savePayroll(Employee emp, int month, int amount) {
	    // First, check if a payroll row already exists for the given employee and month
	    Payroll existingPayroll = payrollRepository.findByEmployeeAndMonthId(emp, month);

	    if (existingPayroll != null) {
	        // Payroll record exists, update the amount
	        existingPayroll.setAmount(amount);
	        return payrollRepository.save(existingPayroll); // Update the existing record
	    } else {
	        // Payroll record doesn't exist, create a new one
	        Payroll newPayroll = new Payroll();
	        newPayroll.setEmployee(emp);
	        newPayroll.setMonthId(month);
	        newPayroll.setAmount(amount);
	        return payrollRepository.save(newPayroll); // Save the new record
	    }
	}

}
