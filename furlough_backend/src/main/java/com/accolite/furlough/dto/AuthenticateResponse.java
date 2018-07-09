package com.accolite.furlough.dto;

import com.accolite.furlough.entity.Admin;

public class AuthenticateResponse {
	
	private Admin adminDetails;
	private boolean userFound;
	
	
	
	public AuthenticateResponse(Admin adminDetails, boolean userFound) {
		super();
		this.adminDetails = adminDetails;
		this.userFound = userFound;
	}
	
	
	public Admin getAdminDetails() {
		return adminDetails;
	}
	public void setAdminDetails(Admin adminDetails) {
		this.adminDetails = adminDetails;
	}
	public boolean isUserFound() {
		return userFound;
	}
	public void setUserFound(boolean userFound) {
		this.userFound = userFound;
	}
	
	
	
}
