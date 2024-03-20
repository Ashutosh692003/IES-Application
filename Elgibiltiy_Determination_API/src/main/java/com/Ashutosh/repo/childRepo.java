package com.Ashutosh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Ashutosh.entity.childEntity;

public interface childRepo extends JpaRepository<childEntity,Integer> {
	 @Query(value = "SELECT  *  FROM DC_CHILDREN WHERE Case_Number =:caseNum" ,nativeQuery=true)       
	   public List<childEntity> findByCase_Number(Integer caseNum);
}
