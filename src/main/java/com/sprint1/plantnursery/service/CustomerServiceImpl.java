package com.sprint1.plantnursery.service;

import java.util.List;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint1.plantnursery.entity.Customer;
import com.sprint1.plantnursery.exceptions.UserNotFoundException;
import com.sprint1.plantnursery.repository.ICustomerRepository;

/*Controller Class for Customer Controller
Author : Arigela Raghuram
*/

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	// method to find the details of the customer using customer Id

	@Override
	public Customer getCustomer(int customerId) throws UserNotFoundException {
		Optional<Customer> findById = customerRepository.findById(customerId);

		return findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));
	}

	// method to get all the customers

	@Override
	public List<Customer> getAllCustomers() throws UserNotFoundException {

		if (customerRepository.findAll().isEmpty())
			throw new UserNotFoundException("There are no records");

		return customerRepository.findAll();

	}

	// method to delete the customer using customer Id

	@Override
	public Customer deleteCustomer(int customerId) throws UserNotFoundException {
		/*Optional<Customer> findById = customerRepository.findById(customerId);
		findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));
		customerRepository.deleteById(customerId);
		return findById.get();*/       // Hussain-13

		Optional<Customer> findById = customerRepository.findById(customerId);
		if(findById.isPresent()) {
			customerRepository.deleteById(customerId);
		}
		findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + customerId));
		Customer c1=findById.get();
		return c1;
	}

	// method to add the customer using

	@Override
	public Customer addNewCustomer(Customer customer) {
		//customerRepository.save(customer);
		//return customer;    // Hussain-13
		Customer customer1=customerRepository.save(customer);
		return customer1;
	}

	// method to update the customer using customer Id
	@Override
	public Customer updateCustomer(Customer customer, int customerId) throws UserNotFoundException {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (customerOptional.isPresent()) {
			Customer customerToBeUpdated = customerOptional.get();
			/*customerToBeUpdated.setCustomerId(customer.getCustomerId());// by Hussain-13 (we have to write the code for single value update also)
			customerToBeUpdated.setCustomerName(customer.getCustomerName());
			customerToBeUpdated.setCustomerEmail(customer.getCustomerEmail());
			customerToBeUpdated.setUsername(customer.getUsername());
			customerToBeUpdated.setPassword(customer.getPassword());
			customerToBeUpdated.setCustomerId(customer.getCustomerId());// by Hussain-13 (no need to provide id in body @postman)*/
			if(customer.getCustomerName()!=null){
				customerToBeUpdated.setCustomerName(customer.getCustomerName());
			}
			if(customer.getCustomerEmail()!=null) {
				customerToBeUpdated.setCustomerEmail(customer.getCustomerEmail());
			}
			if(customer.getUsername()!=null) {
				customerToBeUpdated.setUsername(customer.getUsername());
			}
			if(customer.getPassword()!=null) {
				customerToBeUpdated.setPassword(customer.getPassword());
			}
			customerRepository.save(customerToBeUpdated);
		}
		return customerOptional.orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}

	@Override
	public Customer validateCustomer(int customerId, String username, String password) throws UserNotFoundException {
		if (username == null && password == null) {
			System.out.println("Values cannot be empty");
		}
		//new code from line number 87 to 124   // by Hussain-13 
		Optional<Customer> customer1 = customerRepository.findById(customerId);
		Customer returnCustomer=null;
		if (customer1.isPresent()) {
			Customer customer2= customer1.get();
			if (username.equals(customer2.getUsername()) && password.equals(customer2.getPassword())) {
				System.out.println("Valid User ");
				returnCustomer=	customer2;
			}
			else {
				System.out.println("invalid user username or password");
				throw new UserNotFoundException("Invalid username or password. please enter valid details....");
			}
		}
		else {
			System.out.println("user not found with id "+customerId);
			throw new UserNotFoundException("User not found with id "+customerId);
		}
		return returnCustomer;
	}
}