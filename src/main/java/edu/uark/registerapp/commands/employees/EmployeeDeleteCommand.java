package edu.uark.registerapp.commands.employees;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeDeleteCommand implements VoidCommandInterface {
	// Variable and Property
	private UUID employeeId;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	@Override
	public void execute() {
		final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findById(this.employeeId);
		// If no record with the associated record ID exists in the database throw exception
		if (!employeeEntity.isPresent()) { 
			throw new NotFoundException("Product");
		}
		// Delete employee
		this.employeeRepository.delete(employeeEntity.get());
	}

	// Getter and setter functions
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeDeleteCommand setEmployeeId(final UUID productId) {
		this.employeeId = productId;
		return this;
	}
}
