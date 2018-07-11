package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccoliteEmployee")
public class AccoliteEmployee {

    public AccoliteEmployee() {
        super();
    }

    public AccoliteEmployee(final String employeeID, final String employeeName, final String employeeEmail,
            final String employeeContact, final String mSID) {
        super();
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeContact = employeeContact;
        this.mSID = mSID;
    }

    @Id
    @Column(name = "employeeID")
    private String employeeID;

    @Column(name = "employeeName")
    private String employeeName;

    @Column(name = "employeeEmail")
    private String employeeEmail;

    @Column(name = "employeeContact")
    private String employeeContact;

    // @OneToOne(mappedBy = "accoliteEmployee")
    @Column(name = "mSID")
    private String mSID;

    public String getmSID() {
        return mSID;
    }

    public void setmSID(final String mSID) {
        this.mSID = mSID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(final String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(final String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(final String employeeContact) {
        this.employeeContact = employeeContact;
    }

}
