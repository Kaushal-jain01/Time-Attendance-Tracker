package com.example.TimesheetManagement.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timesheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date;

	private LocalTime clockInTime;
	private LocalTime clockOutTime;
	

	private Integer totalHoursWorked;
	private Integer extraHoursWorked;

	@ManyToOne
    @JsonBackReference // This will not serialize the back reference to avoid infinite loop
    private Employee employee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(LocalTime clockInTime) {
		this.clockInTime = clockInTime;
	}

	public LocalTime getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(LocalTime clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public Integer getTotalHoursWorked() {
		return totalHoursWorked;
	}

	public void setTotalHoursWorked(Integer totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Integer getExtraHoursWorked() {
		return extraHoursWorked;
	}

	public void setExtraHoursWorked(Integer extraHoursWorked) {
		this.extraHoursWorked = extraHoursWorked;
	}

}