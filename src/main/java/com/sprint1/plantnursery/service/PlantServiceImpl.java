package com.sprint1.plantnursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.plantnursery.entity.Plant;
import com.sprint1.plantnursery.exceptions.PlantIdNotFoundException;
import com.sprint1.plantnursery.repository.IPlantRepository;

/*Service Class for Plant
Author :Arigela Raghuram
*/

@Service 
public class PlantServiceImpl implements IPlantService
{
	@Autowired
	private IPlantRepository plantRepo;
	
	@Override
	public Plant addNewPlant(Plant plant) {
		//plantRepo.save(plant);
		//return plant;   // Hussain-13
		return plantRepo.save(plant);
	}
	
	@Override
	public Plant updatePlant(Plant plant, int id){
		Optional<Plant> plantOptional = plantRepo.findById(id);
		if(plantOptional.isPresent()) {
			Plant plantToBeUpdated = plantOptional.get();
			/*plantToBeUpdated.setBloomTime(plant.getBloomTime()); // Hussain-13
			plantToBeUpdated.setName(plant.getName());     	 //we can't change single value. if we try to change all remaining values stores null or 0's.
			plantToBeUpdated.setPlantCost(plant.getPlantCost()); //String stores null and integers and double stores 0.
			plantToBeUpdated.setPlantDescription(plant.getPlantDescription());
			plantToBeUpdated.setDifficultyLevel(plant.getDifficultyLevel());
			plantToBeUpdated.setPlantHeight(plant.getPlantHeight());
			plantToBeUpdated.setMedicinalOrCulinaryUse(plant.getMedicinalOrCulinaryUse());
			plantToBeUpdated.setPlantSpread(plant.getPlantSpread());
			plantToBeUpdated.setPlantsStock(plant.getPlantsStock());
			plantToBeUpdated.setTemparature(plant.getTemparature());
			plantToBeUpdated.setTypeOfPlant(plant.getTypeOfPlant());*/
			if(plant.getBloomTime()!=null) {		//we can update a single variable or multiple values //Hussain-13
				plantToBeUpdated.setBloomTime(plant.getBloomTime()); 
			}
			if(plant.getName()!=null) {
				plantToBeUpdated.setName(plant.getName());
			}
			if(plant.getPlantCost()!=0) {
				plantToBeUpdated.setPlantCost(plant.getPlantCost());
			}
			if(plant.getPlantDescription()!=null) {
				plantToBeUpdated.setPlantDescription(plant.getPlantDescription());
			}
			if(plant.getDifficultyLevel()!=null) {
				plantToBeUpdated.setDifficultyLevel(plant.getDifficultyLevel());
			}
			if(plant.getPlantHeight()!=0) {
				plantToBeUpdated.setPlantHeight(plant.getPlantHeight());
			}
			if(plant.getMedicinalOrCulinaryUse()!=null) {
				plantToBeUpdated.setMedicinalOrCulinaryUse(plant.getMedicinalOrCulinaryUse());
			}
			if(plant.getPlantSpread()!=null) {
				plantToBeUpdated.setPlantSpread(plant.getPlantSpread());
			}
			if(plant.getPlantsStock()!=0) {
				plantToBeUpdated.setPlantsStock(plant.getPlantsStock());
			}
			if(plant.getTemparature()!=null) {
			plantToBeUpdated.setTemparature(plant.getTemparature());
			}
			if(plant.getTypeOfPlant()!=null) {
			plantToBeUpdated.setTypeOfPlant(plant.getTypeOfPlant());
			}
			plantRepo.save(plantToBeUpdated);
			return plantToBeUpdated;
			
		}
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}
	
	@Override
	public void deletePlant(int plantId){
		
		Optional<Plant> plantOptional = plantRepo.findById(plantId);
		
		if(plantOptional.isPresent()) {
			Plant here = plantOptional.get();
			plantRepo.delete(here);
		}
			/*return here;
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
		}*/  // delete method return type should be void,  and no need to print the deleted data --  Hussain-13
		if(plantOptional.isEmpty()){ //return is needed, if we want to send the Http status to browser. 
			throw new PlantIdNotFoundException("Plant Not Found");}
	}
	
	@Override
	public Plant getPlant(int plantId){
		Optional<Plant> plantOptional = plantRepo.findById(plantId);
		if(plantOptional.isPresent()) {
			
			return plantRepo.findById(plantId).get();
		}
		
		return plantOptional.orElseThrow(() -> new PlantIdNotFoundException("Plant Not Found"));
	}
	
	
	@Override
	public List<Plant> getAllPlants() {
		List<Plant> plantList=plantRepo.findAll();
		if(plantList.isEmpty()) {											// Hussain-13 
			throw new PlantIdNotFoundException("No records found.....");//should throw the exception if the table is empty
		}
		return plantList;
		
	}
}
	
	