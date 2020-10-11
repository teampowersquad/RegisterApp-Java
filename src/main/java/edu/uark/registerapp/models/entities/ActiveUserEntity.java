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

@Entity
@Table(name="activeuser")
public class ActiveUserEntity {
	// Variables
	private final UUID id;
	private UUID employeeId;
	private String name;
	private int classification;
	private String sessionKey;
	private LocalDateTime createdOn;

	// Constructor
	public ActiveUserEntity() {
		this.id = new UUID(0, 0);
		this.classification = -1;
		this.name = StringUtils.EMPTY;
		this.employeeId = new UUID(0, 0);
		this.sessionKey = StringUtils.EMPTY;
    }

	@Id
	// Create column for Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	//Id getter function
	public UUID getId() {
		return this.id;
	}

	// Create column for Employee Id
	@Column(name = "employeeid")
	// Employee Id getter and setter functions
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public ActiveUserEntity setEmployeeId(final UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	// Create column for name
	@Column(name = "name")
	// Name getter and setter functions
	public String getName() {
		return this.name;
	}
	public ActiveUserEntity setName(final String name) {
		this.name = name;
		return this;
	}

	// Create column for classification
	@Column(name = "classification")
	// Classification getter and setter functions
	public int getClassification() {
		return this.classification;
	}
	public ActiveUserEntity setClassification(final int classification) {
		this.classification = classification;
		return this;
	}

	// Create column for Session Key
	@Column(name = "sessionkey")
	// Session Key getter and setter functions
	public String getSessionKey() {
		return this.sessionKey;
	}
	public ActiveUserEntity setSessionKey(final String sessionKey) {
    	this.sessionKey = sessionKey;
    	return this;
    }

	// Create column to track of date employee was Created On
    @Column(name="createdon", insertable=false, updatable = false)
	@Generated(GenerationTime.INSERT)
	// Getter function to get what date employee was Created On
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
}