package com.Ashutosh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Ashutosh.entity.educationEntity;
import com.Ashutosh.entity.incomeEntity;

public interface incomeRepo extends JpaRepository<incomeEntity,Integer>{
	  @Query(value = "SELECT  *  FROM DC_INCOME WHERE case_num_case_num = :caseNum" ,nativeQuery=true)
	   public incomeEntity findBycaseNum(Integer caseNum)	;
}
