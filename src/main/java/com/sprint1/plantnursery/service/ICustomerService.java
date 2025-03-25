package com.sprint1.plantnursery.service;

import java.util.List;


import org.springframework.stereotype.Service;
import com.sprint1.plantnursery.entity.Customer;
import com.sprint1.plantnursery.exceptions.UserNotFoundException;

/*Controller Class for Customer Controller
Author : Arigela Raghuram
*/

@Service
public interface ICustomerService {
	Customer addNewCustomer(Customer customer);
	Customer deleteCustomer(int customerId) throws UserNotFoundException;
	Customer getCustomer(int customerId) throws UserNotFoundException;
	Customer updateCustomer(Customer customer,int customerId) throws UserNotFoundException;
	List<Customer> getAllCustomers() throws UserNotFoundException;
	Customer validateCustomer(int customerId,String username, String password)throws UserNotFoundException;
}