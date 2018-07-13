package com.accolite.furlough.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class AccoliteEmployeeDTO {

	private String employeeID;

	private String employeeName;

	private String employeeEmail;

	private String employeeContact;

	private String mSID;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
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

	public String getmSID() {
		return mSID;
	}

	public void setmSID(String mSID) {
		this.mSID = mSID;
	}
	

}
