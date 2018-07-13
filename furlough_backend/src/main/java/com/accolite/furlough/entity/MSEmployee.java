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
        // TODO Auto-generated constructor stub
    }

    public MSEmployee(final String mSID, final String resourceName, final String vendorName, final String division,
            final String officeLocation, final String accoliteEmployee, final String email, final String contact) {
        super();
        MSID = mSID;
        this.resourceName = resourceName;
        this.vendorName = vendorName;
        this.division = division;
        this.officeLocation = officeLocation;
        this.accoliteEmployee = accoliteEmployee;
        this.email = email;
        this.contact = contact;
    }

    @Id
    @Column(name = "MSID")
    private String MSID;

    @Column(name = "resourceName")
    private String resourceName;

    @Column(name = "vendorName")
    private String vendorName;

    @Column(name = "division")
    private String division;

    @Column(name = "officeLocation")
    private String officeLocation;

    @Column(name = "accoliteEmployee")
    private String accoliteEmployee;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(final String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(final String division) {
        this.division = division;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(final String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getMSID() {
        return MSID;
    }

    public void setMSID(final String mSID) {
        MSID = mSID;
    }

    public String getAccoliteEmployee() {
        return accoliteEmployee;
    }

    public void setAccoliteEmployee(final String accoliteEmployee) {
        this.accoliteEmployee = accoliteEmployee;
    }

}
