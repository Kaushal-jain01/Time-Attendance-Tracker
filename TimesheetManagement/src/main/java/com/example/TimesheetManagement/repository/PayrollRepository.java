package com.example.TimesheetManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

	Payroll findByEmployeeAndMonthId(Employee emp, int month);

}
