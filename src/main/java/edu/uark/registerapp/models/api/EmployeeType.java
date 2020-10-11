package edu.uark.registerapp.models.api;

import org.apache.commons.lang3.StringUtils;

import edu.uark.registerapp.models.enums.EmployeeClassification;

public class EmployeeType {
	// Variables
	private int classification;
	private String displayLabel;

	// Constructors
	public EmployeeType() {
		this(-1, StringUtils.EMPTY);
	}
	public EmployeeType(final EmployeeClassification employeeClassification) {
		this(
			employeeClassification.getClassification(),
			employeeClassification.getDisplayLabel());
	}
	public EmployeeType(final int classification, final String displayLabel) {
		this.displayLabel = displayLabel;
		this.classification = classification;
	}

	// Classification getter and setter functions
	public int getClassification() {
		return this.classification;
	}
	public EmployeeType setClassification(final int classification) {
		this.classification = classification;
		return this;
	}

	// Display Label getter and setter functions
	public String getDisplayLabel() {
		return this.displayLabel;
	}
	public EmployeeType setDisplayLabel(final String displayLabel) {
		this.displayLabel = displayLabel;
		return this;
	}

	// Array to keep track of all employee type information
	public static EmployeeType[] allEmployeeTypes() {
		final EmployeeClassification[] employeeClassifications =
			EmployeeClassification.values();
		final EmployeeType[] employeeTypes =
			new EmployeeType[employeeClassifications.length];
		for (int i = 0; i < employeeClassifications.length; i++) {
			employeeTypes[i] = new EmployeeType(employeeClassifications[i]);
		}
		return employeeTypes;
	}
}
