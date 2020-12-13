package com.carmgmt.model;

public class EmployeeDetails {
    private int employeeId;
    private String employeeName;
    private int employeeMblNum;
    private String email;
    private int userId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeMblNum() {
        return employeeMblNum;
    }

    public void setEmployeeMblNum(int employeeMblNum) {
        this.employeeMblNum = employeeMblNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
