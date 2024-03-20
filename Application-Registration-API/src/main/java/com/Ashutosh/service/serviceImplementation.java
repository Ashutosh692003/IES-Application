package com.Ashutosh.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.appEntityBinding;
import com.Ashutosh.entity.appEntity;
import com.Ashutosh.entity.roleEntity;
import com.Ashutosh.entity.userEntity;
import com.Ashutosh.repo.applicationRepo;
import com.Ashutosh.repo.userRepo;

@Service
public class serviceImplementation implements service {
	
	             @Autowired
	             applicationRepo appRepo;
	             
	              @Autowired
	              userRepo uRepo;

	@Override
	public boolean register(appEntityBinding appData) {
		                   appEntity appInfo =            appRepo.findByemail(appData.getEmail())   ;  
		                       if(appInfo!=null) {
		                    	    return false;
		                       }
		                                    
		                       appEntity appDetails = new appEntity();
		                       BeanUtils.copyProperties(appData, appDetails);

		   		            
		   		     if(appData.getDobString()!=null && appData.getDobString().isEmpty()==false) {
		   		    	  String dob = appData.getDobString();
		   		    	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   		    	   LocalDate localDate = LocalDate.parse(dob, formatter);
		   		    	      appDetails.setDob(localDate);
		   		    	      
		   		     } 
		   		                           Optional< userEntity> us = uRepo.findById(appData.getUserId());
		   		                                   userEntity user = us.get();
		   		                           
		   		     appDetails.setCreatedBy(user);
		   		     appRepo.save(appDetails);
		                         
		                         
		return true;
	}

	@Override
	public List<appEntityBinding> viewAll(Integer uid) {
		          Optional<userEntity> us = uRepo.findById(uid);
		          userEntity user = us.get();
		                  roleEntity role = user.getRoleId();
		                    
		                    Integer roleid = role.getId();
		                    
		List<appEntity> appEntities = null;
		                if(roleid==1)  {       
		          appEntities = appRepo.findAll();}
		                else {
		                	 appEntities = appRepo.getAllByuserId(uid);
		                }
		                           List<appEntityBinding> appData = new ArrayList();
		                           
		                            for(appEntity appEntity :appEntities) {
		                            	 appEntityBinding appBind = new appEntityBinding(); 
		                            	  BeanUtils.copyProperties(appEntity, appBind);
		                            	  appData.add(appBind);
		                            }
		return appData;
	}
       
	        
}
