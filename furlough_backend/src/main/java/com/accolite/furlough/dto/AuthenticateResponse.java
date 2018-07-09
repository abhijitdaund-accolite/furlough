package com.accolite.furlough.dto;

import com.accolite.furlough.entity.AdminModel;

public class AuthenticateResponse {
	
	private AdminModel adminDetails;
	private boolean userFound;
	
	
	
	public AuthenticateResponse(AdminModel adminDetails, boolean userFound) {
		super();
		this.adminDetails = adminDetails;
		this.userFound = userFound;
	}
	
	
	public AdminModel getAdminDetails() {
		return adminDetails;
	}
	public void setAdminDetails(AdminModel adminDetails) {
		this.adminDetails = adminDetails;
	}
	public boolean isUserFound() {
		return userFound;
	}
	public void setUserFound(boolean userFound) {
		this.userFound = userFound;
	}
	
	
	
}
