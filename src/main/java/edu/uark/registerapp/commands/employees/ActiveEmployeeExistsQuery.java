package edu.uark.registerapp.commands.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class ActiveEmployeeExistsQuery implements VoidCommandInterface {
	// Property
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void execute() {
		// If there are no active employee records in the database then throw a NotFoundException
		if (!this.employeeRepository.existsByIsActive(true)) {
			throw new NotFoundException("Employee");
		}
	}
}