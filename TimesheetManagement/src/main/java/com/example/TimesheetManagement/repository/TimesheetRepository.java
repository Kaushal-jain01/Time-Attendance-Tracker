package com.example.TimesheetManagement.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TimesheetManagement.model.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
	Optional<Timesheet> findByEmployeeIdAndDate(Integer empId, LocalDate date);
	List<Timesheet> findByEmployeeIdAndDateBetween(Integer employeeId, LocalDate startDate, LocalDate endDate);
}

