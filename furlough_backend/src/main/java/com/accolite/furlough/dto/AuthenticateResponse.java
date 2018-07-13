package com.accolite.furlough.dto;

import com.accolite.furlough.entity.Admin;

public class AuthenticateResponse {

    private Admin adminDetails;
    private boolean userFound;

    public AuthenticateResponse(final Admin adminDetails, final boolean userFound) {
        super();
        this.adminDetails = adminDetails;
        this.userFound = userFound;
    }

    public Admin getAdminDetails() {
        return adminDetails;
    }

    public void setAdminDetails(final Admin adminDetails) {
        this.adminDetails = adminDetails;
    }

    public boolean isUserFound() {
        return userFound;
    }

    public void setUserFound(final boolean userFound) {
        this.userFound = userFound;
    }

}
