package com.Ashutosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.binding.plansBinding;
import com.Ashutosh.binding.viewPlanBinding;
import com.Ashutosh.entity.plansEntity;
import com.Ashutosh.repo.planRepo;
import com.Ashutosh.service.planService;

@RestController
public class plansController {
	 
	   @Autowired
	   planService planServiceImpl;
	   
	   @Autowired
	   planRepo pRepo;
	   
	   
	   
	   
                
	              @PostMapping("/createPlan")
	           public boolean createPlan(@RequestBody plansBinding plan) {
	            	      
	            return 	  planServiceImpl.createPlan(plan);
	            	   
	              }
	               
	                  @GetMapping("/updatePlan")
	           public plansEntity updatePlan(@RequestParam("planId") Integer planId) {
	        	           plansEntity plan =        planServiceImpl.updatePlan(planId) ;  
	        	           return plan;
	           }

	 	             @GetMapping("/planActiveStatus")
	 	         public String activeStatus(@RequestParam("planId") Integer planId) {
	 	        	                 String str=           planServiceImpl.actvieStatus(planId);         
	 	        	     return str;
	 	         }
	 	          
	 	                 @GetMapping("/viewPlans")
	 	             public List<viewPlanBinding> viewPlans(){
	 	            	List<viewPlanBinding> plans =           planServiceImpl.viewPlan();
	 	            	 return plans;
	 	             }
	 	             
	 
}
