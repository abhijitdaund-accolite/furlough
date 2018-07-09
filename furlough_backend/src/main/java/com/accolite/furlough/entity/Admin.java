package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.accolite.furlough.utils.AdminRoleLevels;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    private String employeeID;

    @Column(name = "password")
    private String password;

    @Column(name = "adminRoles")
    private AdminRoleLevels adminRoles;

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(final String employeeID) {
        this.employeeID = employeeID;
    }

    public AdminRoleLevels getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(final AdminRoleLevels adminRoles) {
        this.adminRoles = adminRoles;
    }
}
