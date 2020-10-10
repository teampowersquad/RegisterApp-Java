package edu.uark.registerapp.commands.employees;

import java.util.Arrays;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;
import edu.uark.registerapp.commands.exceptions.UnauthorizedException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeSignInCommand implements ResultCommandInterface<Employee> {
	// Variables and Properties
	private String sessionId;
	private EmployeeSignIn employeeSignIn;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ActiveUserRepository activeUserRepository;

	@Override
	public Employee execute() {
		this.validateProperties();
		return new Employee(this.SignInEmployee());
	}

	// Validate the incoming request object, catch errors
	private void validateProperties() {
		if (StringUtils.isBlank(this.employeeSignIn.getEmployeeId())) {
			throw new UnprocessableEntityException("employee ID");
		}
		try {
			Integer.parseInt(this.employeeSignIn.getEmployeeId());
		} catch (final NumberFormatException e) {
			throw new UnprocessableEntityException("employee ID");
		}
		if (StringUtils.isBlank(this.employeeSignIn.getPassword())) {
			throw new UnprocessableEntityException("password");
		}
	}

	// Query the employee by their employee ID
	// findByEmployeeId == queryByEmployeeId
	@Transactional
	private EmployeeEntity SignInEmployee() {
		final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findByEmployeeId(Integer.parseInt(this.employeeSignIn.getEmployeeId()));
		// If employee is not present or correct length, check error
		if (!employeeEntity.isPresent() || !Arrays.equals(
				employeeEntity.get().getPassword(),
				EmployeeHelper.hashPassword(this.employeeSignIn.getPassword()))
		) {
			throw new UnauthorizedException();
		}
		final Optional<ActiveUserEntity> activeUserEntity =
			this.activeUserRepository.findByEmployeeId(employeeEntity.get().getId());
		// If the user is not present, create a new one in the database
		if (!activeUserEntity.isPresent()) {
			this.activeUserRepository.save(
					(new ActiveUserEntity())
						.setSessionKey(this.sessionId)
						.setEmployeeId(employeeEntity.get().getId())
						.setClassification(
							employeeEntity.get().getClassification())
						.setName(
							employeeEntity.get().getFirstName()
								.concat(" ")
								.concat(employeeEntity.get().getLastName())));
		// If user is present, update session key using save method
		} else {
			this.activeUserRepository.save(
				activeUserEntity.get().setSessionKey(this.sessionId));
		}
		return employeeEntity.get();
	}

	// Employee Sign In getter and setter functions
	public EmployeeSignIn getEmployeeSignIn() {
		return this.employeeSignIn;
	}
	public EmployeeSignInCommand setEmployeeSignIn(final EmployeeSignIn employeeSignIn) {
		this.employeeSignIn = employeeSignIn;
		return this;
	}

	// Session Id getter and setter functions
	public String getSessionId() {
		return this.sessionId;
	}
	public EmployeeSignInCommand setSessionId(final String sessionId) {
		this.sessionId = sessionId;
		return this;
	}
}
