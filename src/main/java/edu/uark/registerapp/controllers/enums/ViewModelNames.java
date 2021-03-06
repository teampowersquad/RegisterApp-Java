package edu.uark.registerapp.controllers.enums;

public enum ViewModelNames {
	// Enum elements
	NOT_DEFINED(""),
	ERROR_MESSAGE("errorMessage"),
	PRODUCTS("products"), // Product listing
	PRODUCT("product"), // Product detail
	EMPLOYEE_ID("employeeId"), 
	EMPLOYEE("employee"),
	EMPLOYEE_TYPES("employeeTypes"),
	IS_ELEVATED_USER("isElevatedUser");
	
	// Variables
	private String value;

	// Getter and setter functions
	public String getValue() {
		return value;
	}
	private ViewModelNames(final String value) {
		this.value = value;
	}
}
