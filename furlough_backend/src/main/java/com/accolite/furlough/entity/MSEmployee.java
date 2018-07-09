package com.accolite.furlough.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MSEmployee")
public class MSEmployee {

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeID")
    private AccoliteEmployee accoliteEmployee;

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

}
