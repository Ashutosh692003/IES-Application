package com.Ashutosh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ashutosh.entity.userEntity;

public interface userRepo  extends JpaRepository<userEntity,Integer>{

}
