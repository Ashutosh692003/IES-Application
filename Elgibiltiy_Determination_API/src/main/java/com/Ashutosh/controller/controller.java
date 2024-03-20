package com.Ashutosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.binding.eligEntityBinding;
import com.Ashutosh.service.serviceInterface;

@RestController
public class controller {
	
	 @Autowired
	 serviceInterface serviceImpl;
	 
                      @GetMapping("/elig-determine/{caseNum}")
	           public ResponseEntity< eligEntityBinding> eligdetermination(@PathVariable("caseNum")Integer caseNum) {
	        	              
                    	eligEntityBinding response =      serviceImpl.eligDetermin(caseNum);
                    	return new ResponseEntity<>(response,HttpStatus.OK);
                    	  
	           }
	           
	            
	            @GetMapping("/generate-correspondence/{caseNum}")
	         public ResponseEntity<String> generateCo(@PathVariable("caseNum")Integer caseNum){
				                 String resp =              serviceImpl.generateCorrespondence(caseNum);
				                  if("Success".equals(resp)) {
				                	       return new ResponseEntity<>("Correspondece Generated SucessFully",HttpStatus.CREATED);
				                  }
				                  else {
				                	  return new ResponseEntity<>("Correspondece Not Generated....",HttpStatus.INTERNAL_SERVER_ERROR);   
				                  }
	        	             
	         }
}
