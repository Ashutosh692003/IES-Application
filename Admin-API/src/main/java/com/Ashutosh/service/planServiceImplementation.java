package com.Ashutosh.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.plansBinding;
import com.Ashutosh.binding.viewPlanBinding;
import com.Ashutosh.entity.plansEntity;
import com.Ashutosh.entity.userEntity;
import com.Ashutosh.repo.planRepo;

@Service
public class planServiceImplementation implements planService {
	 
                          @Autowired
                          planRepo pRepo;
	@Override
	public boolean createPlan(plansBinding plan) {
		                      plansEntity planDetails =new plansEntity();
		                     BeanUtils.copyProperties(plan, planDetails);
		                     
		                 if(plan.getPlanEndDateString()!=null && plan.getPlanEndDateString().isEmpty()==false) {
		                	 String dob = plan.getPlanEndDateString();
		 		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 		            LocalDate localDate = LocalDate.parse(dob, formatter);
		 		                     planDetails.setPlanEndDate(localDate);
		 		                	 
		                 }
		                 if(plan.getPlanStartDateString()!=null && plan.getPlanStartDateString().isEmpty()==false) {
		                	 String dob = plan.getPlanStartDateString();
		 		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 		            LocalDate localDate = LocalDate.parse(dob, formatter);
		 		                     planDetails.setPlanStartDate(localDate);
		 		                	 
		                 }
		                 planDetails.setActvieSt("Y");
		                 
		                // todo      creator and updater entity logic
		                 
		                 pRepo.save(planDetails);
		                 
		return true;
	}
	@Override
	public plansEntity updatePlan(Integer planId) {
		         Optional<plansEntity>  plan =            pRepo.findById(planId);
		                                      plansEntity planDetails  = plan.get();
		                                     return planDetails;                      	
	}
	@Override
	public String actvieStatus(Integer planId) {
		Optional<plansEntity>   pl =   pRepo.findById(planId);
        plansEntity plan = pl.get()  ;  
         
                              if(plan.getActvieSt().equals("Y")) {
                              	
                              	plan.setActvieSt("N");
                              	 pRepo.save(plan);
                              	 return "Plan Deactivated Sucessfully";
                              }
                              else {
                              plan.setActvieSt("Y");
                              pRepo.save(plan);
                             	 return "Plan Activated Sucessfully";
                              }
		
	}
	@Override
	public List<viewPlanBinding> viewPlan() {
		     List<plansEntity> plansEntities  =pRepo.findAll();
		     List<viewPlanBinding> list = new ArrayList<>();
		                 for(plansEntity p:plansEntities) {
		                	    viewPlanBinding view = new viewPlanBinding();
		                	     BeanUtils.copyProperties(p, view);
		                	    list.add(view);
		                	     
		                 }
		return list; 
	}
               
}
