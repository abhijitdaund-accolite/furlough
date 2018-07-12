package com.accolite.furlough.dto;

import javax.persistence.Column;

public class MSEmployeeDTO {

    private String MSID;


    private String resourceName;


    private String vendorName;


    private String division;


    private String officeLocation;


    private String accoliteEmployee;


	public String getMSID() {
		return MSID;
	}


	public void setMSID(String mSID) {
		MSID = mSID;
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


	public String getAccoliteEmployee() {
		return accoliteEmployee;
	}


	public void setAccoliteEmployee(String accoliteEmployee) {
		this.accoliteEmployee = accoliteEmployee;
	}
    
}
