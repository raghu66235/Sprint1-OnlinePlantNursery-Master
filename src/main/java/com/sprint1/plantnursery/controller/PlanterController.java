package com.sprint1.plantnursery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.plantnursery.entity.Planter;
import com.sprint1.plantnursery.service.IPlanterService;

/*Controller Class for Planter Controller
Created By:Arigela Raghuram
*/
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/planter")
public class PlanterController {

	@Autowired
	private IPlanterService planterService;

	/****************************
	 * Method: addPlanter Description: It is used to add into the planter table
	 * 
	 * @returns planter It returns String type message
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type.
	 * 
	 ****************************/

	@PostMapping("/addPlanter")
	public ResponseEntity<Planter> addPlanter(@RequestBody Planter planter) {
		Planter planter1 = planterService.addPlanter(planter);
		return new ResponseEntity<Planter>(planter1, HttpStatus.CREATED);
	}

    /****************************
	 * Method: getAllplanters
	 * Description: It is used to view all planter items in planter table
	 * @returns planter It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/
	
	@GetMapping("/getAllPlanters")
	public ResponseEntity<List<Planter>> getAllPlanters() {
		List<Planter> planters = planterService.viewAllPlanters();
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
	}
	
    /****************************
	 * Method: getPlanter by Id
	 * Description: It is used to view all planter items in planter table
	 * @returns planter It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/

	@GetMapping("/getPlanter/id/{id}")
	public ResponseEntity<Planter> getPlanterById(@PathVariable int id) {
		Planter planter = planterService.getPlanter(id);
		return new ResponseEntity<Planter>(planter, HttpStatus.OK);
	}

	/****************************
	 * Method: Update planter Description: It is used to update planter in planter
	 * table
	 * 
	 * @returns planter It returns String type message
	 * @PutMapping: It is used to handle the HTTP POST requests matched with given
	 *              URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type.
	 * 
	 ****************************/

	@PutMapping("/updatePlanter/{planterId}")
	public ResponseEntity<Planter> update(@RequestBody Planter planter,@PathVariable("planterId") int id ) {
		Planter newPlanter = planterService.updatePlanter(planter,id);
		return new ResponseEntity<Planter>(newPlanter, HttpStatus.OK);
	}

	/****************************
	 * Method: deletePlanter Description: It is used to remove the items in the planter table
	 * @returns planter It returns String type message
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with
	 *                 given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. Created
	 *               By-NagaJyothi,Sumanjali Created Date - 9-1-2022
	 ****************************/

	@DeleteMapping("/deletePlanter/id/{planterId}")
	public ResponseEntity<String> deletePlanter(@PathVariable int planterId) {
	//public ResponseEntity<Planter> deletePlanter(@PathVariable int planterId) {
		// logger.trace("deleting the whole plant");
		//return new ResponseEntity<Planter>(planterService.deletePlanter(planterId), HttpStatus.OK);
		//Planter planter1=planterService.deletePlanter(planterId);// 
		planterService.deletePlanter(planterId);
		/*if(planter1==null) {
			//return new ResponseEntity<String>("Id not found ",HttpStatus.NOT_FOUND);
		}*/
		return new ResponseEntity<String>("Planter Deleted with id : "+planterId,HttpStatus.OK);
	}

	
    /****************************
	 * Method: getPlanter by min cost and max cost
	 * Description: It is used to view all planter items in planter table
	 * @returns planter It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/
	
	@GetMapping("/planters/{min}/{max}")
	public ResponseEntity<List<Planter>> getAllPlantersInRange(@PathVariable double min, @PathVariable double max) {
		List<Planter> planters = planterService.viewAllPlanters(min, max);
		if (planters != null) {
			return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		}
		else { 
			return new ResponseEntity<List<Planter>>(planters, HttpStatus.NOT_FOUND);
		}
	}
}
