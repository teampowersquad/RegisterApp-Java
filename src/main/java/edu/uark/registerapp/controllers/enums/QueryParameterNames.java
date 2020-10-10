package edu.uark.registerapp.controllers.enums;

public enum QueryParameterNames {
	// Enum elements
	NOT_DEFINED(""),
	ERROR_CODE("errorCode"), 
	EMPLOYEE_ID("employeeId");

	// Variable
	private String value;
	
	// Getter and setter functions
	public String getValue() {
		return value;
	}
	private QueryParameterNames(final String value) {
		this.value = value;
	}
}
