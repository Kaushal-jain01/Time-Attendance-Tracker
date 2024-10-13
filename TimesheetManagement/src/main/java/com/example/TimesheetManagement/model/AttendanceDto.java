package com.example.TimesheetManagement.model;

public class AttendanceDto {
    private String employeeName;
    private String month;
    private Integer totalWorkingDays;
    private Integer totalDaysPresent;
    private Integer totalDaysAbsent;
    private Integer extraHoursWorked;

    // Constructor
    public AttendanceDto(String employeeName, String month, Integer totalWorkingDays,
                         Integer totalDaysPresent, Integer totalDaysAbsent,
                         Integer extraHoursWorked) {
        this.employeeName = employeeName;
        this.month = month;
        this.totalWorkingDays = totalWorkingDays;
        this.totalDaysPresent = totalDaysPresent;
        this.totalDaysAbsent = totalDaysAbsent;
        this.extraHoursWorked = extraHoursWorked;
    }

    // Getters and Setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Integer getTotalDaysPresent() {
        return totalDaysPresent;
    }

    public void setTotalDaysPresent(Integer totalDaysPresent) {
        this.totalDaysPresent = totalDaysPresent;
    }

    public Integer getTotalDaysAbsent() {
        return totalDaysAbsent;
    }

    public void setTotalDaysAbsent(Integer totalDaysAbsent) {
        this.totalDaysAbsent = totalDaysAbsent;
    }

    public Integer getExtraHoursWorked() {
        return extraHoursWorked;
    }

    public void setExtraHoursWorked(Integer extraHoursWorked) {
        this.extraHoursWorked = extraHoursWorked;
    }
}
