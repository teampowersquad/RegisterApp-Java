package edu.uark.registerapp.commands.employees;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeCreateCommand implements ResultCommandInterface<Employee> {
	// Properties
	@Autowired
	private EmployeeRepository employeeRepository;
	private Employee apiEmployee;

	// Constructor
	public EmployeeCreateCommand() {
		this.isInitialEmployee = false;
	}

	@Override
	public Employee execute() {
		this.validateProperties();
		if (this.isInitialEmployee) {
			this.apiEmployee.setClassification(EmployeeClassification.GENERAL_MANAGER.getClassification());
		}
		// Create a new ENTITY object from the API object details.
		final EmployeeEntity employeeEntity =
			this.employeeRepository.save(new EmployeeEntity(this.apiEmployee));
        // Set employee to database - map employee to object
		this.apiEmployee.setId(employeeEntity.getId());
		this.apiEmployee.setPassword(StringUtils.EMPTY);
		this.apiEmployee.setCreatedOn(employeeEntity.getCreatedOn());
		this.apiEmployee.setEmployeeId(EmployeeHelper.padEmployeeId(employeeEntity.getEmployeeId()));
		return this.apiEmployee;
	}

	// Helper methods - validatation function to make sure employee is found
	private void validateProperties() {
		if (StringUtils.isBlank(this.apiEmployee.getFirstName())) {
			throw new UnprocessableEntityException("first name");
		}
		if (StringUtils.isBlank(this.apiEmployee.getLastName())) {
			throw new UnprocessableEntityException("last name");
		}
		if (StringUtils.isBlank(this.apiEmployee.getPassword())) {
			throw new UnprocessableEntityException("password");
		}
		if (!this.isInitialEmployee
			&& (EmployeeClassification.map(this.apiEmployee.getClassification()) == EmployeeClassification.NOT_DEFINED)) {

			throw new UnprocessableEntityException("classification");
		}
	}

    // Getter and setter functions
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeCreateCommand setApiEmployee(final Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	private boolean isInitialEmployee;
	public boolean getIsInitialEmployee() {
		return this.isInitialEmployee;
	}
	public EmployeeCreateCommand setIsInitialEmployee(
		final boolean isInitialEmployee
	) {
		this.isInitialEmployee = isInitialEmployee;
		return this;
	}
}
