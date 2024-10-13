package com.example.TimesheetManagement.model;

public class UserIdRoleDto {

    private Integer id;
    private String role;

    // Constructor
    public UserIdRoleDto(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "UserIdRoleDto{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}

