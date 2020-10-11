package edu.uark.registerapp.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;
import edu.uark.registerapp.models.api.Employee;

// Create entity for table of employees
@Entity
@Table(name="employee")
public class EmployeeEntity {
	// Variables
	private final UUID id;
	private int employeeId;
	private String firstName;
	private String lastName;
	private byte[] password;
	private boolean isActive;
	private int classification;
	private UUID managerId;
	private LocalDateTime createdOn;

	// Constructors
	public EmployeeEntity() {
		this.employeeId = -1;
		this.isActive = false;
		this.id = new UUID(0, 0);
		this.classification = -1;
		this.password = new byte[0];
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
	}
	public EmployeeEntity(final Employee apiEmployee) {
    	this.id = new UUID(0, 0);
		this.isActive = apiEmployee.getIsActive();
		this.lastName = apiEmployee.getLastName();
		this.firstName = apiEmployee.getFirstName();
		this.classification = apiEmployee.getClassification();
		this.password = EmployeeHelper.hashPassword(apiEmployee.getPassword());
		this.managerId = (
			(apiEmployee.getManagerId() != null)
				? apiEmployee.getManagerId()
				: new UUID(0, 0));
	}

	@Id
	// Create column for Id
    @Column(name="id", updatable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	// Id getter function
	public UUID getId() {
		return this.id;
	}

	// Create column for Employee Id
	@Column(name = "employeeid", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	// Id getter function
	public int getEmployeeId() {
		return this.employeeId;
	}

	// Create column for First Name
	@Column(name = "firstname")
	// First Name getter and setter function
	public String getFirstName() {
		return this.firstName;
	}
	public EmployeeEntity setFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	// Create column for Last Name
	@Column(name = "lastname")
	// Last Name getter and setter functions
	public String getLastName() {
		return this.lastName;
	}
	public EmployeeEntity setLastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	// Create column for Password
	@Column(name = "password")

	// Password getter and setter functions
	public byte[] getPassword() {
		return this.password;
	}
	public EmployeeEntity setPassword(final byte[] password) {
		this.password = password;
		return this;
	}

	// Create column to check if Active
	@Column(name = "active")

	// Is Active getter and setter functions
	public boolean getIsActive() {
		return this.isActive;
	}
	public EmployeeEntity setIsActive(final boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	// Create column for Classification
	@Column(name = "classification")

	// Classification getter and setter functions
	public int getClassification() {
		return this.classification;
	}
	public EmployeeEntity setClassification(final int classification) {
		this.classification = classification;
		return this;
	}

	// Create column for Manager Id
	@Column(name = "managerid")

	// Manager Id getter and setter functions
	public UUID getManagerId() {
		return this.managerId;
	}
	public EmployeeEntity setManagerId(final UUID managerId) {
		this.managerId = managerId;
		return this;
	}

	// Create column to track when user was Created On
	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)

	// Getter function to get what date employee was Created On
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}

	// Set all variables 
	public Employee synchronize(final Employee apiEmployee) {
		this.setIsActive(apiEmployee.getIsActive());
		this.setLastName(apiEmployee.getLastName());
		this.setFirstName(apiEmployee.getFirstName());
		this.setClassification(apiEmployee.getClassification());
		if (apiEmployee.getManagerId() != null) {
			this.setManagerId(apiEmployee.getManagerId());
		}
		if (!StringUtils.isBlank(apiEmployee.getPassword())) {
			this.setPassword(
				EmployeeHelper.hashPassword(
					apiEmployee.getPassword()));
		}
		apiEmployee.setId(this.getId());
		apiEmployee.setCreatedOn(this.getCreatedOn());
		apiEmployee.setEmployeeId(this.getEmployeeId());
		return apiEmployee;
	}
}
