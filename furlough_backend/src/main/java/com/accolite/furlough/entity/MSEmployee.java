package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MSEmployee")
public class MSEmployee {

    public MSEmployee() {
        super();
    }

    @Id
    @Column(name = "mSID")
    private String mSID;

    @Column(name = "resourceName")
    private String resourceName;

    @Column(name = "officeLocation")
    private String officeLocation;

    @Column(name = "accoliteEmployee")
    private String accoliteEmployee;

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(final String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getmSID() {
        return mSID;
    }

    public void setmSID(final String mSID) {
        this.mSID = mSID;
    }

    public MSEmployee(final String mSID, final String accoliteEmployee, final String email, final String officeLocation,
            final String resourceName) {
        super();
        this.mSID = mSID;
        this.resourceName = resourceName;
        this.officeLocation = officeLocation;
        this.accoliteEmployee = accoliteEmployee;
        this.email = email;
    }

    public String getAccoliteEmployee() {
        return accoliteEmployee;
    }

    public void setAccoliteEmployee(final String accoliteEmployee) {
        this.accoliteEmployee = accoliteEmployee;
    }

}
