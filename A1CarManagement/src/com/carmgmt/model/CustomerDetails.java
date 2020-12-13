package com.carmgmt.model;

public class CustomerDetails {
    private int customerId;
    private String customerName;
    private int customerMblNum;
    private String email;
    private int userId;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerMblNum() {
        return customerMblNum;
    }

    public void setCustomerMblNum(int customerMblNum) {
        this.customerMblNum = customerMblNum;
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
