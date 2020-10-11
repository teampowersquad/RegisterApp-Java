package edu.uark.registerapp.models.api;

import org.apache.commons.lang3.StringUtils;

public class EmployeeSignIn {
    // Variables
    private String employeeId;
    private String password;

    // Constructor
    public EmployeeSignIn() {
        this.employeeId = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
    }
    
    // Employee ID getter and setter functions
    public String getEmployeeId() {
        return this.employeeId;
    }
    public EmployeeSignIn setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    // Password getter and setter functions
    public String getPassword() {
        return this.password;
    }
    public EmployeeSignIn setPassword(final String password) {
        this.password = password;
        return this;
    }
}
