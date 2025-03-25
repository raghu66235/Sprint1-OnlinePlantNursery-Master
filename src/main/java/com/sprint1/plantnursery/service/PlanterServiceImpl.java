package com.sprint1.plantnursery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.plantnursery.entity.Planter;
import com.sprint1.plantnursery.exceptions.ResourceNotFoundException;
import com.sprint1.plantnursery.exceptions.InsufficientStockException;
import com.sprint1.plantnursery.repository.IPlanterRepository;

/*Controller Class for Planter Controller
Created By: Arigela Raghuram
*/

@Service
@Transactional
public class PlanterServiceImpl implements IPlanterService {

	@Autowired
	private IPlanterRepository iplanterrepository;
	

	@Override
	public Planter addPlanter(Planter planter) {
		/*if (planter.getPlanterId() == 0)
			return iplanterrepository.save(planter);
		Optional<Planter> optionalPlanter = iplanterrepository.findById(planter.getPlanterId()); 
		if (optionalPlanter.isPresent()) {
			Planter p = optionalPlanter.get();
			p.setPlanterStock(p.getPlanterStock()+1);
			return iplanterrepository.save(p); // unwanted extra code  - by Hussain-13
		}else {*/
			return iplanterrepository.save(planter);
		//}
	}

	@Override
	public Planter deletePlanter(int planterId) {
		Optional<Planter> optionalPlanter = iplanterrepository.findById(planterId); 
		if (optionalPlanter.isPresent()) {
			Planter p = optionalPlanter.get();
			p.setPlanterStock(p.getPlanterStock()-1);
			if (p.getPlanterStock() < 0) {
				throw new InsufficientStockException("Stock is insufficient");}
			iplanterrepository.delete(p);
		}
		return optionalPlanter.orElseThrow(() -> new ResourceNotFoundException("The planter with given id does not exist"));
	}
	
	@Override
	public Planter getPlanter(int id) {
		Optional<Planter> optionalPlanter =  iplanterrepository.findById(id); 
		return optionalPlanter.orElseThrow(() -> new ResourceNotFoundException("Planter does not exist with given id"));
	}
	@Override
	/*public Planter updatePlanter(Planter planter) {
		Optional<Planter> optionalPlanter = iplanterrepository.findById(planter.getPlanterId());  
		if (optionalPlanter.isPresent()) {
			iplanterrepository.save(planter);*/  // By Hussain-13
	public Planter updatePlanter(Planter planter,int planterId) {
		Optional<Planter> optionalPlanter = iplanterrepository.findById(planterId);  
		if (optionalPlanter.isPresent()) {
			Planter planter1=optionalPlanter.get();
			
			if(planter.getPlanterheight()!=0) {
				planter1.setPlanterheight(planter.getPlanterheight());
			}
			if(planter.getPlanterCapacity()!=0) {
				planter1.setPlanterCapacity(planter.getPlanterCapacity());
			}
			if(planter.getDrainageHoles()!=0) {
				planter1.setDrainageHoles(planter.getDrainageHoles());
			}
			if(planter.getPlanterColor()!=null) {
				planter1.setPlanterColor(planter.getPlanterColor());
			}
			if(planter.getPlanterShape()!=null) {
				planter1.setPlanterShape(planter.getPlanterShape());
			}
			if(planter.getPlanterStock()!=0) {
				planter1.setPlanterStock(planter.getPlanterStock());
			}
			if(planter.getPlanterCost()!=0) {
				planter1.setPlanterCost(planter.getPlanterCost());
			}
		}
		return optionalPlanter.orElseThrow(() -> new ResourceNotFoundException("Planter with given id does not exist. So, update can not be done"));
	}

	@Override
	public List<Planter> viewAllPlanters() {
		//return iplanterrepository.findAll(); //Hussain-13
		List<Planter> list=iplanterrepository.findAll();
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("No data available");
		}
		return list;
	}

	@Override
	public List<Planter> viewAllPlanters(double minCost, double maxCost) {
		List<Planter> allPlanters = iplanterrepository.findAll();
		List<Planter> displayPlanter=null;
		List<Planter> requiredPlanters = allPlanters.stream().filter((p) -> p.getPlanterCost() >minCost && p.getPlanterCost() < maxCost).collect(Collectors.toList());
		if(requiredPlanters.size()==0) {
			throw new ResourceNotFoundException("No details found in the database between range "+minCost+ "and "+maxCost);
		}				    //Hussain-13  - Exception added
		else {
			displayPlanter=requiredPlanters; 
		}
		return displayPlanter;
		
	}

}
