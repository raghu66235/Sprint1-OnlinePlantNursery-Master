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

import com.sprint1.plantnursery.service.IPlantService;

import com.sprint1.plantnursery.entity.Plant;
import com.sprint1.plantnursery.exceptions.PlantIdNotFoundException;

/*Controller Class for Plant Controller
Author :Arigela Raghuram
*/
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/plantsModule")
public class IPlantController {
	
	@Autowired
	private IPlantService plantService;
	
	/****************************
	 * Method: addPlant 
	 * @RequestMapping: It is used to map HTTP requests to handler methods of MVC and REST controllers.
	 * @RestController: It is used to create RESTful web services using MVC.
	 * Description: It is used to add into the plant table 
	 * @returns plant It returns String type message
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/
	
	@PostMapping("/addPlant")
	public ResponseEntity<Plant> addNewPlant(@RequestBody Plant plant) {
		System.out.println("Add Plant...");
		Plant plant1=plantService.addNewPlant(plant);
		return new ResponseEntity<Plant>(plant1, HttpStatus.CREATED);
	}
// ========================================================================================================================================	
	
	/****************************
	 * Method: Update plant 
	 * Description: It is used to update plant in plant table
	 * @returns plant It returns String type message 
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/
	@PutMapping({"/UpdatePlant/{id}"})
	public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant, @PathVariable int id) {
		// logger.trace("updating the whole plant having id "+ id);  
		Plant updatedPlant=plantService.updatePlant(plant, id);
		return new ResponseEntity<Plant>(updatedPlant, HttpStatus.ACCEPTED);
	}
// ========================================================================================================================================	

	
	/****************************
	 * Method: deletePlant by Id
	 * Description: It is used to remove the items in the plant table
	 * @returns plant It returns String type message 
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 *
	 ****************************/
	
	@DeleteMapping("/deletePlant/{id}")
	public ResponseEntity<String> deletePlant(@PathVariable int id) throws PlantIdNotFoundException{
		//logger.trace("deleting the whole plant");	
		//return new ResponseEntity<Plant>(plantService.deletePlant(id), HttpStatus.OK);
		plantService.deletePlant(id);   // Hussain-13
		return new ResponseEntity<String>("Plant Deleted with id : "+id, HttpStatus.OK);
	}

// ========================================================================================================================================	
	
	/****************************
	 * Method: viewAllPlants
	 * Description: It is used to view all order items in plant table
	 * @returns plant It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 ****************************/
	
	@GetMapping("/getAllPlants")
	public ResponseEntity<List<Plant>> viewAllPlants() {
		//logger.trace("fetching all the plants");
		List<Plant> plant1=plantService.getAllPlants();
		if(plant1.isEmpty()) {    //Hussain-13
			//throw new PlantIdNotFoundException("No data available....");
			return new ResponseEntity<List<Plant>>(plant1, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Plant>>(plant1, HttpStatus.OK);
	}

// ========================================================================================================================================	
	/****************************
	 * Method: viewPlant by Id
	 * Description: It is used to view all order items in plant table
	 * @returns plant It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 ****************************/
	
	@GetMapping("/getPlant/{id}")
	public ResponseEntity<Plant> viewPlant(@PathVariable Integer id) {
		//logger.trace("fetching the plant with id "+ id);
		return new ResponseEntity<Plant>(plantService.getPlant(id), HttpStatus.FOUND);
	}

	

	
}
