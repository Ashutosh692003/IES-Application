package com.Ashutosh.service;

import java.util.List;

import com.Ashutosh.binding.plansBinding;
import com.Ashutosh.binding.viewPlanBinding;
import com.Ashutosh.entity.plansEntity;

public interface planService {
     public boolean createPlan(plansBinding plan) ;
    	 
     public plansEntity updatePlan(Integer planId);
     
     public String actvieStatus(Integer planId);
     
     public List<viewPlanBinding> viewPlan();
}
