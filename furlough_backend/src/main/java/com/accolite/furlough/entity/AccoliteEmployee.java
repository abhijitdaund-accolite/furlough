package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccoliteEmployee")
public class AccoliteEmployee {

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
    @Column(name = "msid")
    private String msEmployee;

    public String getMsEmployee() {
        return msEmployee;
    }

    public void setMsEmployee(final String msEmployee) {
        this.msEmployee = msEmployee;
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
