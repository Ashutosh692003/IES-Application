package com.Ashutosh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.reportBinding;
import com.Ashutosh.binding.responseBinding;
import com.Ashutosh.entity.appEntity;
import com.Ashutosh.entity.entityEligibility;
import com.Ashutosh.repo.appEntityRepo;
import com.Ashutosh.repo.eligibilityRepo;

@Service
public class service  implements serviceInterface{
	
	              @Autowired
	              eligibilityRepo elRepo;
	              
	              @Autowired
	              appEntityRepo aRepo;

	@Override
	public List<responseBinding> getReports(reportBinding reportCriteria) {
		                            entityEligibility entity  = new entityEligibility();
		                 if(reportCriteria.getPlanStatus()!=null && reportCriteria.getPlanStatus().isEmpty()==false) {
		                	 entity.setPlanStatus(reportCriteria.getPlanStatus());  
		                 }
		                 
		                 if(reportCriteria.getPlanName()!=null && reportCriteria.getPlanName().isEmpty()==false) {
		                	 entity.setPlanName(reportCriteria.getPlanName()); 
		                 }
		                 if(reportCriteria.getEligStartDate()!=null) {
		                	 entity.setEligStartDate(reportCriteria.getEligStartDate()); 
		                 }
		                 if(reportCriteria.getEligEndDate()!=null) {
		                	 entity.setEligStartDate(reportCriteria.getEligEndDate()); 
		                 }
		                
		                 
		              List<entityEligibility>  entities =          elRepo.findAll(Example.of(entity)) ; 
		              List<responseBinding> response = new ArrayList<>();
		                              
		                for(entityEligibility obj :entities)
		                {
		                	              Integer caseNum  = obj.getCaseNum();
		                	         responseBinding resp = new responseBinding();
		                	                 appEntity app = aRepo.findById(caseNum).get();
		                	              resp.setName(app.getName());
		                	              resp.setGender(app.getGender());
		                	              resp.setEmail(app.getEmail());
		                	              resp.setPhNo(app.getPhNo());
		                	              resp.setSsn(app.getSsn());
		                	              resp.setCaseNum(app.getCaseNum());
		                	              
		                	           response.add(resp);
		                	              
		                	                 
		                }
		                 
		return response;
	}
                
}
