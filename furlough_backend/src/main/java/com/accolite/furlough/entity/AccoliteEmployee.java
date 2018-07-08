package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="accolite_employee_bootstrap")
public class AccoliteEmployee {
	
	@Id
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name="EMPLOYEE_EMAIL")
	private String employeeEmail;
	
	@Column(name="EMPLOYEE_CONTACT")
	private String employeeContact;
	
	@OneToOne(mappedBy="accoliteEmployee")
	private MSEmployee msEmployee;
	
	
	
	public MSEmployee getMsEmployee() {
		return msEmployee;
	}
	public void setMsEmployee(MSEmployee msEmployee) {
		this.msEmployee = msEmployee;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeContact() {
		return employeeContact;
	}
	public void setEmployeeContact(String employeeContact) {
		this.employeeContact = employeeContact;
	}
	
	
	
	
}
