package com.sprint1.plantnursery.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.plantnursery.entity.Planter;

/*Controller Class for Planter Controller
Created By: Arigela Raghuram
*/

@Repository
public interface IPlanterRepository extends JpaRepository<Planter, Integer> {

}
