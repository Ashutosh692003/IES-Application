package com.Ashutosh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.binding.appEntityBinding;
import com.Ashutosh.entity.appEntity;
import com.Ashutosh.service.service;

@RestController
public class controller {
	  
	  Logger log = LoggerFactory.getLogger(controller.class);
	 
	                     @Autowired
	                       service serviceImpl;
	                     
	             @PostMapping("/register")
                public ResponseEntity<Boolean> register(@RequestBody appEntityBinding appData) {
	                  log.debug("Registration Process Started........");
                	boolean b=       serviceImpl.register(appData);
                	 if(b==true) {
                		  log.info("Registration Sucessfully done....");
                	 }
                	 else {
                		  log.info("Registration Failed");
                	 }
                	    return new ResponseEntity<>(b,HttpStatus.OK);
                }
	             @GetMapping("/viewAllApplication/{uid}")
	             public List<appEntityBinding> viewAll(@PathVariable Integer uid){
	            	  log.debug("Fetching Process of Applications Started");
	            	    List<appEntityBinding>    appData =          serviceImpl.viewAll(uid);
	            	      log.info("Fetching Process Ended");
	            	   return appData;
	             }
}
