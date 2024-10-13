package com.example.TimesheetManagement.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimesheetManagement.model.AttendanceDto;
import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.MonthModel;
import com.example.TimesheetManagement.model.Payroll;
import com.example.TimesheetManagement.model.Timesheet;
import com.example.TimesheetManagement.repository.EmployeeRepository;
import com.example.TimesheetManagement.repository.MonthRepository;
import com.example.TimesheetManagement.repository.PayrollRepository;
import com.example.TimesheetManagement.repository.TimesheetRepository;

@Service
public class TimesheetService {

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private MonthRepository monthRepository;
	
	@Autowired
	private PayrollService payrollService;

	public Timesheet clockIn(Integer employeeId, LocalDate date, LocalTime clockInTime) {
		// Checking if a timesheet already exists for that employee and date
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		Optional<Timesheet> existingTimesheet = timesheetRepository.findByEmployeeIdAndDate(employeeId, date);

		if (existingTimesheet.isPresent()) {
			Timesheet timesheet = existingTimesheet.get();

			// Only set the clock-in time if it's not already set (first clock-in)
			if (timesheet.getClockInTime() == null) {
				timesheet.setClockInTime(clockInTime);
				timesheetRepository.save(timesheet);
			}

			return timesheet;
		} else {
			// If no timesheet exists, create a new one and set the clock-in time
			Timesheet timesheet = new Timesheet();
			timesheet.setEmployee(employee);
			timesheet.setDate(date);
			timesheet.setClockInTime(clockInTime);
			return timesheetRepository.save(timesheet);
		}
	}

	public Timesheet clockOut(Integer employeeId, LocalDate date, LocalTime clockOutTime) {
		// Fetch the timesheet for that employee and date
		Optional<Timesheet> existingTimesheet = timesheetRepository.findByEmployeeIdAndDate(employeeId, date);

		if (existingTimesheet.isPresent()) {

			Timesheet timesheet = existingTimesheet.get();
			// Always set the clock-out time (overwrite previous if any)
			timesheet.setClockOutTime(clockOutTime);

			// Calculate total hours worked
			Duration totalHours = null;

			if (timesheet.getClockInTime() != null && timesheet.getClockOutTime() != null) {

				totalHours = Duration.between(timesheet.getClockInTime(), timesheet.getClockOutTime());
				long hours = totalHours.toHours();
				int hoursWorked = (int) hours;
				timesheet.setTotalHoursWorked(hoursWorked);
				if (hoursWorked > 9)
					timesheet.setExtraHoursWorked(hoursWorked - 9);
				else
					timesheet.setExtraHoursWorked(0);
				System.out.println("Total Hours set Successfully!");
			}

			System.out.println("Clocked Out Successfully!");
			timesheetRepository.save(timesheet);

			return timesheet;

		} else {
			throw new RuntimeException("Clock-out not allowed without a prior clock-in on the same day");
		}
	}

	public Integer getHoursWorked(Integer employeeId, LocalDate date) {
		Timesheet timesheet = timesheetRepository.findByEmployeeIdAndDate(employeeId, date)
				.orElseThrow(() -> new RuntimeException("Timesheet not found for this date"));

		Duration totalHours = null;

		if (timesheet.getClockInTime() != null && timesheet.getClockOutTime() != null) {
			totalHours = Duration.between(timesheet.getClockInTime(), timesheet.getClockOutTime());
		}

		long hours = totalHours.toHours();
		int hoursWorked = (int) hours;
		timesheet.setTotalHoursWorked(hoursWorked);
		if (hoursWorked > 9)
			timesheet.setExtraHoursWorked(hoursWorked - 9);
		else
			timesheet.setExtraHoursWorked(0);

		return hoursWorked;
	}

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
		payrollService.savePayroll(employee, month, amount);
		return amount;
	}

	public AttendanceDto getAttendanceReport(Integer empId, Integer month) {

		Employee emp = employeeRepository.findById(empId).get();
		String empName = emp.getName();

		MonthModel monthObj = monthRepository.findById(month).get();
		String monthName = monthObj.getMonthName();

		int workingDays = monthRepository.findWorkingDaysById(month).get();

		int endDay = monthRepository.findTotalDaysById(month).get();
		System.out.println("endDay: " + endDay);
		LocalDate startDate = LocalDate.of(2024, month, 1);
		LocalDate endDate = LocalDate.of(2024, month, endDay);
		List<Timesheet> timesheetList = timesheetRepository.findByEmployeeIdAndDateBetween(empId, startDate, endDate);

		int daysPresent = timesheetList.size();
		System.out.println("daysPresent: " + daysPresent);

		Integer extraHrsWorked = 0;
		for (Timesheet t : timesheetList) {
			if(t.getExtraHoursWorked() != null) {
				extraHrsWorked += t.getExtraHoursWorked();
			}
		}

		int extraHrs = (extraHrsWorked == null) ? 0 : extraHrsWorked;
		System.out.println("extra hrs: --->" + extraHrs);

		int daysAbsent = workingDays - daysPresent;

		return new AttendanceDto(empName, monthName, workingDays, daysPresent, daysAbsent, extraHrs);

	}

}
