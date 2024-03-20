package com.Ashutosh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ashutosh.entity.plansEntity;

public interface planRepo  extends JpaRepository<plansEntity,Integer>{
           
}
