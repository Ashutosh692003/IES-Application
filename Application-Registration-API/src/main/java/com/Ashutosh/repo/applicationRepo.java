package com.Ashutosh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Ashutosh.entity.appEntity;

public interface applicationRepo extends JpaRepository<appEntity ,Integer> {
                    
	          public appEntity findByemail(String email);
	          @Query(value = "SELECT  *  FROM  App_Registration_Data WHERE user_id = :id" ,nativeQuery=true)
	          public List<appEntity> getAllByuserId(Integer id);
	          
}
