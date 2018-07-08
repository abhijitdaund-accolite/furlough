package com.accolite.furlough.entity;

import javax.persistence.*;

import com.accolite.furlough.utils.AdminRoleLevels;

@Entity
@Table(name="admin_details")
public class AdminModel {

	@Id
	private String employeeId;
	
	@Column(name="PASSWORD")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="ADMIN_ROLES")
	private AdminRoleLevels adminRoles;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public AdminRoleLevels getAdminRoles() {
		return adminRoles;
	}
	public void setAdminRoles(AdminRoleLevels adminRoles) {
		this.adminRoles = adminRoles;
	}
	
	
}
