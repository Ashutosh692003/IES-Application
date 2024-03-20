package com.Ashutosh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Ashutosh.entity.plansEntity;

public interface plansRepo extends JpaRepository<plansEntity,Integer>{
                            
	  @Query(value = "SELECT  *  FROM  IES_PLANS WHERE actvieSt = 'Y' " ,nativeQuery=true)        
	  public List<plansEntity> getActivePlans();
	
}
