package com.Ashutosh.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.childEntityBinding;
import com.Ashutosh.binding.childListBinding;
import com.Ashutosh.binding.educationEntityBinding;
import com.Ashutosh.binding.incomeEntityBinding;
import com.Ashutosh.binding.planselectionBinding;
import com.Ashutosh.binding.summaryBinding;
import com.Ashutosh.entity.appEntity;
import com.Ashutosh.entity.childEntity;
import com.Ashutosh.entity.educationEntity;
import com.Ashutosh.entity.incomeEntity;
import com.Ashutosh.entity.planSelectionEntity;
import com.Ashutosh.entity.plansEntity;
import com.Ashutosh.entity.userEntity;
import com.Ashutosh.repo.appEntityRepo;
import com.Ashutosh.repo.childRepo;
import com.Ashutosh.repo.educationEntityRepo;
import com.Ashutosh.repo.incomeRepo;
import com.Ashutosh.repo.planSelectionRepo;
import com.Ashutosh.repo.plansRepo;
import com.Ashutosh.repo.userRepo;
@Service
public class serviceImplementation implements  service {
	         
	                         @Autowired
	                          plansRepo plRepo;
	      @Autowired
	      planSelectionRepo pRepo;
           
	       @Autowired
	       incomeRepo inRep;
	       
	       @Autowired
	       appEntityRepo aRepo;
	       
	       @Autowired
	       educationEntityRepo eduRepo;
	       
	       @Autowired
	       childRepo chRepo;
	       
	       @Autowired
	      userRepo uRepo;
	       
	@Override
	public boolean planSelection(planselectionBinding planSelectData) {
	              planSelectionEntity planSelectDetails = new planSelectionEntity();
	              BeanUtils.copyProperties(planSelectData, planSelectDetails);
	             Integer userId = planSelectData.getUserId()    ;
	   Optional<userEntity> us =              uRepo.findById(userId);
	                  if(!us.isEmpty()) {
	                	   planSelectDetails.setUser(us.get());
	                  }
	              
	            pRepo.save(planSelectDetails);
	              
		return true;
	}

	@Override
	public boolean saveIncome(incomeEntityBinding incomeData) {
		         
		                      Integer caseInt = incomeData.getCaseNumInt() ;
		                          Optional<appEntity> caseNum =       aRepo.findById(caseInt) ;
		                          appEntity caseN = caseNum.get();
		                          if(caseN==null) {
		                        	    return false;
		                          }
		                          else {     incomeEntity incomeDetails = new incomeEntity();
		        		          BeanUtils.copyProperties(incomeData, incomeDetails);  
		                        	        incomeDetails.setCaseNum(caseN);
		                        	        Integer userId = incomeData.getUserId();
		                        	 	   Optional<userEntity> us =              uRepo.findById(userId);
		                        	 	                  if(!us.isEmpty()) {
		                        	 	                	    incomeDetails.setUser(us.get());
		                        	 	                  }
		                        	        
		                        	           inRep.save(incomeDetails) ;
		                        	        return true;
		                   
	}}

	@Override
	public boolean saveEducationDetails(educationEntityBinding eduData) {
		                 
		                    Integer caseInt = eduData.getCaseNumInt();
	                          Optional<appEntity> caseNum =       aRepo.findById(caseInt) ;
	                          appEntity caseN = caseNum.get();
	                          if(caseN==null) {
	                        	    return false;
	                          }
	                          else {
	                        	   educationEntity eduDetails = new educationEntity()    ; 
	                        	   BeanUtils.copyProperties(eduData, eduDetails);
	                        	   eduDetails.setCaseNum(caseN);
	                        	   Integer userId = eduData.getUserId();
	                        	   Optional<userEntity> us =              uRepo.findById(userId);
	                        	                  if(!us.isEmpty()) {
	                        	                	   eduDetails.setUser(us.get());
	                        	                  }
	                        	     eduRepo.save(eduDetails);
	                        	     return true;
	                          }
		                        
	}

	@Override
	public boolean saveChildData(childListBinding childData) {
                                     Integer caseNum = childData.getCaseNumInt();
                                      Optional<appEntity>  app=        aRepo.findById(caseNum);
                                           appEntity appDetail = app.get();
                                        
                                     Integer userId  = childData.getUserId();
                                        Optional<userEntity>    us =           uRepo.findById(userId);
                                         userEntity user = us.get();
                                         
                                         
                                        List<childEntityBinding> childs = childData.getChilds();
                                          
                                                     for(childEntityBinding child:childs) {
                                                    	     childEntity ch = new childEntity()  ;
                                                    	   BeanUtils.copyProperties(child, ch);
                                                    	     ch.setUser(user);
                                                    	     ch.setCaseNum(appDetail);
                                                    	       chRepo.save(ch);
                                                     }
                                return true;
	
	}

	@Override
	public Map<Integer, String> planDetails() {
		           Map<Integer,String> plans = new HashMap();
		            List<plansEntity> planEntities = plRepo.getActivePlans();
		            
		              for(plansEntity pEntity : planEntities) {
		            	            Integer id = pEntity.getPlanId();
		            	            String pName= pEntity.getPlanName();
		            	            
		            	      plans.put(id, pName);
		              }
		               
		return plans;
	}

	@Override
	public summaryBinding summary(Integer caseNum) {
		               planSelectionEntity plan = pRepo.findBycaseNum(caseNum)   ;
		                  planselectionBinding plBinding = new planselectionBinding();
		                  
		                  BeanUtils.copyProperties(plan, plBinding);
		                    Optional<plansEntity> p = plRepo.findById(plan.getPlanId());
		                           plansEntity pl = p.get();
		                           String pName = pl.getPlanName();
		                                         userEntity us = plan.getUser();
		                                          Integer id = us.getUserId();
		                                          plBinding.setUserId(id);
		                           plBinding.setPlanName(pName);
		                           
		                           
		                         
		                   incomeEntity income= inRep.findBycaseNum(caseNum);
		                   incomeEntityBinding inBin = new incomeEntityBinding();
		                    inBin.setCaseNumInt(caseNum);
		                    inBin.setUserId(id);
		                    BeanUtils.copyProperties(income, inBin);
		                    
		                    
		            educationEntity edu =          eduRepo.findBycaseNum(caseNum);
		             educationEntityBinding eduBin = new educationEntityBinding();
		             eduBin.setCaseNumInt(caseNum);
		             eduBin.setUserId(id);
		             BeanUtils.copyProperties(edu, eduBin);
		             
		      List<childEntity>  childs=                 chRepo.findByCase_Number(caseNum);
		       
		       childListBinding childsDetail = new childListBinding();
		           Integer userId = null;
		           Integer caseNo=null;
		            
		             userEntity u=null;
		             appEntity app=null;
		           
		             List<childEntityBinding> childBin = new ArrayList();
		           for(childEntity c :childs){
		        	      if(u==null & app==null) {
		        	    u = c.getUser();
		        	    app=c.getCaseNum();
		        	      }
		        	      
		        	    childEntityBinding child = new childEntityBinding();
		        	     BeanUtils.copyProperties(c, child);
		        	      childBin.add(child);
		           }
		           
		           userId = u.getUserId();
		           caseNo = app.getCaseNum();
		              childsDetail.setChilds(childBin);
		              childsDetail.setCaseNumInt(caseNum);
		              childsDetail.setUserId(userId);
		              
		              
		        summaryBinding summary = new summaryBinding()  ;
		                summary.setChilds(childsDetail);
		                summary.setEducation(eduBin);
		                summary.setIncome(inBin);
		                summary.setPlan(plBinding);
		           
		           
		             
		          
		                           
		return summary;
	}

}
