package com.sprint1.plantnursery.service;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.sprint1.plantnursery.entity.Plant;
import com.sprint1.plantnursery.entity.Seed;
import com.sprint1.plantnursery.exceptions.SeedIdNotFoundException;
import com.sprint1.plantnursery.repository.ISeedRepository;

/*Controller Class for Seed Controller
Created By : Arigela Raghuram 
*/

@Service
@Transactional
public class SeedServiceImpl implements ISeedService{
	
	
	@Autowired
	private ISeedRepository seedRepo;

	@Override
	public Seed addSeed(Seed seed) 
	{
		return seedRepo.save(seed);
	}
	
	
	@Override
	public Seed updateSeed(Seed seed, int id) {
		
		Optional<Seed> seedOptional = seedRepo.findById(id);
		if(seedOptional.isPresent()) {
			Seed seedToBeUpdated = seedOptional.get();
			/*seedToBeUpdated.setCommanName(seed.getCommanName());
			seedToBeUpdated.setBloomTime(seed.getBloomTime());
			seedToBeUpdated.setWatering(seed.getWatering());
			seedToBeUpdated.setDifficultyLevel(seed.getDifficultyLevel());
			seedToBeUpdated.setTemprature(seed.getTemprature());
			seedToBeUpdated.setTypeOfSeeds(seed.getTypeOfSeeds());
			seedToBeUpdated.setSeedsDescription(seed.getSeedsDescription());
			seedToBeUpdated.setSeedsStock(seed.getSeedsStock());
			seedToBeUpdated.setSeedsCost(seed.getSeedsCost());
			seedToBeUpdated.setSeedsPerPacket(seed.getSeedsPerPacket());*/    //Hussain-13
			if(seed.getCommanName()!=null) {
				seedToBeUpdated.setCommanName(seed.getCommanName());
			}
			if(seed.getBloomTime()!=null) {
				seedToBeUpdated.setBloomTime(seed.getBloomTime());
			}
			if(seed.getWatering()!=null) {
				seedToBeUpdated.setWatering(seed.getWatering());
			}
			if(seed.getDifficultyLevel()!=null) {
				seedToBeUpdated.setDifficultyLevel(seed.getDifficultyLevel());
			}
			if(seed.getTemprature()!=null) {
				seedToBeUpdated.setTemprature(seed.getTemprature());
			}
			if(seed.getTypeOfSeeds()!=null) {
				seedToBeUpdated.setTypeOfSeeds(seed.getTypeOfSeeds());
			}
			if(seed.getSeedsDescription()!=null) {
				seedToBeUpdated.setSeedsDescription(seed.getSeedsDescription());
			}
			if(seed.getSeedsStock()!=0) {
				seedToBeUpdated.setSeedsStock(seed.getSeedsStock());
			}
			if(seed.getSeedsCost()!=0) {
				seedToBeUpdated.setSeedsCost(seed.getSeedsCost());
			}
			if(seed.getSeedsPerPacket()!=0) {
				seedToBeUpdated.setSeedsPerPacket(seed.getSeedsPerPacket());
			}
			return seedRepo.save(seedToBeUpdated);
			//added 
		}
		//return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Seed Not Found"));
		else {
			throw new SeedIdNotFoundException("Seed Not Found");
		}

}

	
	@Override
	public Seed deleteSeedById(int id) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findById(id);		
		if(seedOptional.isPresent()) {
			seedRepo.delete(seedOptional.get());
		}
		return seedOptional.orElseThrow(()->new SeedIdNotFoundException("Invalid seed id...Cannot delete"));	
	}
	
	@Override
	public Seed getSeed(int id) throws SeedIdNotFoundException{
		Optional<Seed> seedOptional = seedRepo.findById(id);
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Seed Not Found...Invalid ID"));
	}
	

	
	@Override
	public List<Seed> getAllSeeds() {
		//return seedRepo.findAll();
		List<Seed> seedList=seedRepo.findAll();
			//if(seedList.isEmpty()){ //Hussain-13  // there are two types 1.throwing Exception in Service layer or HttpStatus.Not_Found in controller layer
			//throw new SeedIdNotFoundException("No records found.....");}
		return seedList;
	}

	
}
