package com.Ashutosh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Ashutosh.entity.entityEligibility;

public interface eligibilityRepo  extends JpaRepository<entityEligibility,Integer> {
   
	 @Query(value = "Select * from ED_ELIG_DTLS where case_num = :caseNum" ,nativeQuery = true)
	 public entityEligibility findByCaseNum(Integer caseNum);
}
