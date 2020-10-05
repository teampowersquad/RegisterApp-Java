package edu.uark.registerapp.models.api;

import org.apache.commons.lang3.StringUtils;

public class EmployeeSignIn {
    // Define properties
    private String employeeId;
    private String password;

    public EmployeeSignIn() {
        this.employeeId = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
    }
    // Employee ID get & set functions
    public String getEmployeeId() {
        return this.employeeId;
    }
    public EmployeeSignIn setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
        return this;
    }
    // Password get & set functions
    public String getPassword() {
        return this.password;
    }
    public EmployeeSignIn setPassword(final String password) {
        this.password = password;
        return this;
    }
}
