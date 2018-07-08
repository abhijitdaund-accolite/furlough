package com.accolite.furlough.entity;

import javax.persistence.*;

@Entity
@Table(name="ms_employee_bootstrap")
public class MSEmployee {
	
	@Id
	private String msID;
	
	@Column(name="RESOURCE_NAME")
	private String resourceName;
	
	@Column(name="VENDOR_NAME")
	private String vendorName;
	
	@Column(name="DIVISION")
	private String division;
	
	@Column(name="OFFICE_LOCATION")
	private String officeLocation;
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="employeeId")
	private AccoliteEmployee accoliteEmployee;
	
	
	public String getMsID() {
		return msID;
	}

	public void setMsID(String msID) {
		this.msID = msID;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	
	
	
	
}
