package com.example.TimesheetManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MonthModel {
	
	@Id
	private Integer id;
	
	private Integer totalDays;
	private Integer workingDays;
	private String monthName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public int getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	
	
}
