package edu.uark.registerapp.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeClassification {
	// Elum elements
	NOT_DEFINED(-1, "Not Selected"),
	CASHIER(101, "Cashier"),
	SHIFT_MANAGER(501, "Shift Manager"),
	GENERAL_MANAGER(701, "General Manager");
	
    // Variables
    private int classification;
	private String displayLabel;
	private static Map<Integer, EmployeeClassification> valueMap = null;

	// Constructor
	private EmployeeClassification(
		final int classification,
		final String displayLabel
	) {
		this.displayLabel = displayLabel;
		this.classification = classification;
    }
	
	// Getter functions
	public int getClassification() {
		return this.classification;
	}
	public String getDisplayLabel() {
		return this.displayLabel;
	}

	// Map out employee classification using key
	public static EmployeeClassification map(final int key) {
		if (valueMap == null) {
			valueMap = new HashMap<Integer, EmployeeClassification>();
			for (final EmployeeClassification employeeClassification : EmployeeClassification.values()) {
				valueMap.put(
					employeeClassification.getClassification(),
					employeeClassification);
			}
		}
		return ((valueMap.containsKey(key)
			? valueMap.get(key)
			: EmployeeClassification.NOT_DEFINED));
	}

	// Check for elevated user
	public static boolean isElevatedUser(final int classification) {
		final EmployeeClassification employeeClassification =
			EmployeeClassification.map(classification);
		return (
			(employeeClassification == EmployeeClassification.GENERAL_MANAGER)
			|| (employeeClassification == EmployeeClassification.SHIFT_MANAGER));
	}
}