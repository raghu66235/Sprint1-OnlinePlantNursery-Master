package com.sprint1.plantnursery.service;

import com.sprint1.plantnursery.exceptions.UserNotFoundException;

import java.util.List;

import com.sprint1.plantnursery.entity.Admin;

/*Controller Class for Customer Controller
Author : Arigela Raghuram
*/

public interface IAdminService {
	public Admin addAdmin(Admin admin);
	public void removeAdmin(int adminId) throws UserNotFoundException;
	public Admin validateAdmin(int adminId) throws UserNotFoundException;
	public List<Admin> getAllUsers() throws UserNotFoundException;
}
