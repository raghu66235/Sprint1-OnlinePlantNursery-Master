package com.sprint1.plantnursery.controller;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sprint1.plantnursery.entity.Admin;
import com.sprint1.plantnursery.exceptions.UserNotFoundException;
import com.sprint1.plantnursery.service.IAdminService;

/*Controller Class for Customer Controller
Author : Arigela Raghuram
*/

@RequestMapping(value={"/loginController"})
@RestController
public class IAdminController {
	private static final HttpStatus HttpStatus = null;

	@Autowired
	HttpSession session;

	@Autowired
	private IAdminService loginservice;
	
	/****************************
	* Method: addUser
	 * Description: It allows to add the User.
	 * @RequestMapping: It is used to map HTTP requests to handler methods of MVC and REST controllers.
	 * @RestController: It is used to create RESTful web services using MVC.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URL expression.
	 * @Autowired: It enables to inject object dependency implicitly.
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type
     */
	//http://localhost:9092/loginController/addUser
	@PostMapping("/addUser")
	public ResponseEntity<Admin> addUser(@RequestBody Admin admin){
		if(admin.getAdminId() == 0) {
			return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);}
		Admin userImpl=loginservice.addAdmin(admin);
		return new ResponseEntity(userImpl,HttpStatus.OK);
	}
	
//*********************************************************************************************************************	
	
	/****************************
	 * Method: removeUser
	 * Description: It allows to remove the user.
	 * @throws UserNotFoundException 
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URL expression.
     */
	
	@DeleteMapping("/removeUser/{id}")
	public ResponseEntity<String> removeUser(@PathVariable("id") int adminId) throws UserNotFoundException{
		if(adminId==0)
			return new ResponseEntity("Invalid",HttpStatus.NOT_FOUND);
		loginservice.removeAdmin(adminId);
		return new ResponseEntity("User Removed with id: "+adminId,HttpStatus.OK);
	}

//*********************************************************************************************************************	
	
	/****************************
	 * Method: getUserById
	 * Description: It allows to view the user by id.
	 * @throws UserNotFoundException 
	 * @PathVariable: It is used to handle template variables in the request URL
	 * @GetMapping:It is used to handle the HTTP GET requests matched with given URL Expression.
     */
		
	@GetMapping("/viewAllUsers")
	public ResponseEntity<List<Admin>> viewAllUsers() throws UserNotFoundException {
		//logger.trace("fetching all the plants");
		return new ResponseEntity<List<Admin>>(loginservice.getAllUsers(), HttpStatus.OK);
	}
	
	//validateUser  method created by MD.Hussain-13 (method of IAdminService interface)
	@GetMapping("validateUser/{adminId}")
	public ResponseEntity<Admin> validateUser(@PathVariable("adminId") int adminId) throws UserNotFoundException{
		Admin admin=loginservice.validateAdmin(adminId);
		ResponseEntity<Admin> resp1=new ResponseEntity<Admin>(admin,new HttpHeaders(),HttpStatus.FOUND);
		return resp1;
	}
	
//*********************************************************************************************************************	
	
	/**************************** 
	 * Method: signout
	 * Description: It allows to singout the user.
	 * @GetMapping:It is used to handle the HTTP GET requests matched with given URL Expression.
     */
	
	@GetMapping("/signOut")
	public ResponseEntity<Admin> signOut(){
		session.invalidate();
		return new ResponseEntity("LoggedOut",HttpStatus.OK);
	}
}
