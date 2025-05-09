package com.sprint1.plantnursery.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/*Controller Class for Customer Controller
Author :Arigela Raghuram
*/

@Entity
public class Admin {
	@Id
	private int adminId;
	private String adminUsername;
	private String adminPassword;

	public Admin() {
		super();
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}
