package com.Ashutosh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.binding.childEntityBinding;
import com.Ashutosh.binding.childListBinding;
import com.Ashutosh.binding.educationEntityBinding;
import com.Ashutosh.binding.incomeEntityBinding;
import com.Ashutosh.binding.planselectionBinding;
import com.Ashutosh.binding.summaryBinding;
import com.Ashutosh.entity.educationEntity;
import com.Ashutosh.service.service;

@RestController
public class controller {
	
	 @Autowired
	 service serviceImpl;
	           
	        @GetMapping("/plansDisplay")
	    public Map<Integer,String> plansDiplay(){
	        	
	        Map<Integer,String>	  plans =      serviceImpl.planDetails();
	         return plans;
	    	
	    }
	                   @PostMapping("/planSelection")
             public boolean planSelection(@RequestBody planselectionBinding planSelectData) {
            	      boolean b =         serviceImpl.planSelection(planSelectData);
            	       return b;
             }
	                         @PostMapping("/incomeDetails")
	                   public boolean incomeDetails(@RequestBody incomeEntityBinding incomeData) {
	                	         boolean b =         serviceImpl.saveIncome(incomeData);
	                	          return b;
	                   }
	                       
	                         @PostMapping("/educationDetails")
	                   public boolean educationDetails(@RequestBody educationEntityBinding eduData) {
	                	              boolean b =           serviceImpl.saveEducationDetails(eduData);
	                	              return b;
	                   }
	                         
	                         @PostMapping("/childDetails")
	                public boolean childDetials(@RequestBody childListBinding  childsData) {
	                        	boolean b =        serviceImpl.saveChildData(childsData);
	                        	 return b; 
	                         }
	                         
	                       @GetMapping   ("/summary/{caseNum}") 
	                   public summaryBinding summary(@PathVariable("caseNum")Integer caseNum)  {
	                        	       System.out.println(caseNum);
	                  summaryBinding summary =   	             serviceImpl.summary(caseNum);
	                   return summary;
	                    	   
	                	              
	                   }
	                         
	                         // TODO fetching data for summary screen
}
