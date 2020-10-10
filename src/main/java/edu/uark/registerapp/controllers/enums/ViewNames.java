package edu.uark.registerapp.controllers.enums;

public enum ViewNames {
	// Enum elements
	PRODUCT_DETAIL("productDetail"),
	PRODUCT_LISTING("productListing", "/"), 
	EMPLOYEE_DETAIL("employeeDetail"),
	SIGN_IN("signIn", "/"),
	MAIN_MENU("mainMenu");
	
	// Variables
	private String route;
	private String viewName;

	// Getter and setter functions
	public String getRoute() {
		return this.route;
	}
	public String getViewName() {
		return this.viewName;
	}
	private ViewNames(final String viewName) {
		this(viewName, "/".concat(viewName));
	}
	private ViewNames(final String viewName, final String route) {
		this.route = route;
		this.viewName = viewName;
	}
}
