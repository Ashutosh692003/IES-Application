package com.Ashutosh.service;



import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.eligEntityBinding;
import com.Ashutosh.entity.appEntity;
import com.Ashutosh.entity.childEntity;
import com.Ashutosh.entity.coNoticeEntity;
import com.Ashutosh.entity.educationEntity;
import com.Ashutosh.entity.entityEligibility;
import com.Ashutosh.entity.incomeEntity;
import com.Ashutosh.entity.planSelectionEntity;
import com.Ashutosh.entity.plansEntity;
import com.Ashutosh.repo.appEntityRepo;
import com.Ashutosh.repo.childRepo;
import com.Ashutosh.repo.educationEntityRepo;
import com.Ashutosh.repo.eligibilityRepo;
import com.Ashutosh.repo.incomeRepo;
import com.Ashutosh.repo.noticeRepo;
import com.Ashutosh.repo.planSelectionRepo;
import com.Ashutosh.repo.plansRepo;
@Service
public class service implements serviceInterface {

	                 @Autowired
	                 planSelectionRepo psRepo;
	                 
	                 @Autowired
	                 plansRepo pRepo;
	                 
	                 @Autowired
	                 incomeRepo iRepo;
	                 
	                 @Autowired
	                 educationEntityRepo eRepo;
	                 
	                 @Autowired
	                 childRepo chRepo;
	                 
	                 @Autowired
	                 appEntityRepo appRepo;
	                 
	                 @Autowired
	                 eligibilityRepo eligRepo;
	                 
	                 @Autowired
	                 noticeRepo noRepo;
	@Override
	public eligEntityBinding eligDetermin(Integer caseNum) {
		                          planSelectionEntity ps = psRepo.findBycaseNum(caseNum)   ;
		                                          Integer planId = ps.getPlanId();
		                                           plansEntity plan = pRepo.findById(planId).get();
		                                           String planName = plan.getPlanName();
		                                           
		                                  eligEntityBinding response = new eligEntityBinding();
		                                  
		                         incomeEntity income = iRepo.findBycaseNum(caseNum);
		                         educationEntity education = eRepo.findBycaseNum(caseNum);
		                         List<childEntity> children = chRepo.findByCase_Number(caseNum);
		                         response.setPlanName(planName);
		                         response.setCaseNum(caseNum);
		                        
		                          if("SNAP".equals(planName)) {
		                        	   
		                        Integer salary = 	    Integer.parseInt(income.getSalary());
		                        	            if(salary>20000)  {
		                        	            	 response.setPlanStatus("DENIAL"); 
		                        	            	 response.setDenialReason("High Salary Income");
		                        	            }
		                        	            else {
		                        	            	         response.setPlanStatus("APPROVED");
		                        	            	         response.setDenialReason("N/A");
		                        	            	         response.setEligStartDate(LocalDate.now());
		                        	            	         response.setEligEndDate(LocalDate.now().plusMonths(6));
		                        	            	         response.setBenifitAmt(5000);
		                        	            }
		                          }
		                          
		                          else if("CCAP".equals(planName)) {
				                        Integer salary = 	    Integer.parseInt(income.getSalary());
				                        Long childCount = children.stream().count();
				                        boolean ageCondition =true;
				                        
				                        for(childEntity ch:children) {
				                             LocalDate dob = ch.getDob();
				                             Period period = Period.between(dob, LocalDate.now());
				                            
				                             if(period.getYears()>16) {
				                            	      ageCondition = false;
				                             }
				                        }
				                        
				                        	            if(salary>20000)  {
				                        	            	 response.setPlanStatus("DENIAL"); 
				                        	            	 response.setDenialReason("High Salary Income");
				                        	            }
				                        	            else if(childCount==0) {
				                        	            	 response.setPlanStatus("DENIAL"); 
				                        	            	 response.setDenialReason("The application does not currently meet the eligibility criteria of having at least one dependent child.");
				                        	            }
				                        	            else if(ageCondition==false) {
				                        	            	response.setPlanStatus("DENIAL"); 
				                        	            	 response.setDenialReason("The dependent children listed on the application does not meet the age requirement for this program");
				                        	            }
				                        	            else {
				                        	            	         response.setPlanStatus("APPROVED");
				                        	            	         response.setDenialReason("N/A");
				                        	            	         response.setEligStartDate(LocalDate.now());
				                        	            	         response.setEligEndDate(LocalDate.now().plusMonths(6));
				                        	            	         response.setBenifitAmt(4500);
				                        	            }
				                          }
		                          else if("MEDICAID".equals(planName)) {
		                        	  Integer salary = 	    Integer.parseInt(income.getSalary());
		                        	  Integer rent = Integer.parseInt(income.getRentIncome());
		                        	  Integer property = Integer.parseInt(income.getPropertyIncome());
		                        	    Integer extraIncome = rent +property;
		                        	    if(salary>20000)  {
                       	            	 response.setPlanStatus("DENIAL"); 
                       	            	 response.setDenialReason("High Salary Income");
                       	            }
		                        	    else if(extraIncome>0) {
		                        	    	 response.setPlanStatus("DENIAL"); 
	                       	            	 response.setDenialReason("Extra Income Sources...");
		                        	    }
		                        	    else {
               	            	         response.setPlanStatus("APPROVED");
               	            	         response.setDenialReason("N/A");
               	            	         response.setEligStartDate(LocalDate.now());
               	            	         response.setEligEndDate(LocalDate.now().plusMonths(6));
               	            	         response.setBenifitAmt(10000);
               	            }
		                        	     
		                        	  
		                          }
		                          
		                          else if("MEDICARE".equals(planName)) {
		                        	     appEntity   app =     appRepo.findById(caseNum).get();
		                        	           LocalDate dob = app.getDob();
		                        	           Period period = Period.between(dob, LocalDate.now());
		                        	           Integer age = period.getYears();
		                        	           
		                        	           if(65>age) {
		                        	        	   response.setPlanStatus("DENIAL"); 
			                       	            	 response.setDenialReason("Age is less than 65 "); 
		                        	           }
		                        	           else {
		                        	        	   
		                        	        	   response.setPlanStatus("APPROVED");
		                 	            	         response.setDenialReason("N/A");
		                 	            	         response.setEligStartDate(LocalDate.now());
		                 	            	         response.setEligEndDate(LocalDate.now().plusMonths(6));
		                 	            	         response.setBenifitAmt(20000);
		                        	           }
		                          }
		                          else if("RIW".equals(planName)) {
		                        	  Integer salary = 	    Integer.parseInt(income.getSalary());
		                        	    Integer gradYear = education.getGraduationYear();
		                        	    
		                        	     if(salary>0) {
		                        	    	 response.setPlanStatus("DENIAL"); 
	                       	            	 response.setDenialReason("Salary should be nill ");  
		                        	     }
		                        	     else if(gradYear==null) {
		                        	    	 response.setPlanStatus("DENIAL"); 
	                       	            	 response.setDenialReason("Applicant Must be Graduate"); 
		                        	     }
		                        	     else {
		                        	    	 response.setPlanStatus("APPROVED");
                 	            	         response.setDenialReason("N/A");
                 	            	         response.setEligStartDate(LocalDate.now());
                 	            	         response.setEligEndDate(LocalDate.now().plusMonths(6));
                 	            	         response.setBenifitAmt(1500);
		                        	     }
		                          }
		                    
		                       entityEligibility entity = new entityEligibility();
		                         BeanUtils.copyProperties(response, entity);
		                       eligRepo.save(entity);
		                          
		                                          
		return response;
	}
	@Override
	public String generateCorrespondence(Integer caseNum) {
		
		              entityEligibility eligData =          eligRepo.findByCaseNum(caseNum);
		               System.out.println(eligData);
		                                Integer eligId = eligData.getEd_trace_id();
		                            coNoticeEntity co = new coNoticeEntity();
		                                co.setCaseNum(caseNum);
		                                co.setEdgTraceId(eligId);
		                                co.setNoticeStatus("PENDING");
		                                co.setNoticeUrl("n/a");
		                                 
		                                noRepo.save(co);
		                                
		return "Success";
	}

}
