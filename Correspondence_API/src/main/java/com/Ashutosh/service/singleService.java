package com.Ashutosh.service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.eligEntityBinding;
import com.Ashutosh.entity.appEntity;
import com.Ashutosh.entity.coNoticeEntity;
import com.Ashutosh.entity.entityEligibility;
import com.Ashutosh.repo.appEntityRepo;
import com.Ashutosh.repo.eligibilityRepo;
import com.Ashutosh.repo.noticeRepo;
import com.Ashutosh.utility.emailSender;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class singleService implements singleServiceInterface {
	 @Autowired
	 appEntityRepo appRepo;
	               
	     @Autowired
	     eligibilityRepo elRepo;
                                         @Autowired
                                         noticeRepo nRepo;
                                         
                                         @Autowired
                                         emailSender mailSender;
                                         
                                         @Autowired
                                         com.Ashutosh.utility.pdfGenerator pdfGen;
	@Override
	public eligEntityBinding viewCorrespondece(Integer caseNum) {
		                        
		             entityEligibility elig =                elRepo.findByCaseNum(caseNum);
		                   eligEntityBinding bind = new eligEntityBinding();
		                        BeanUtils.copyProperties(elig, bind);
		                       
		return bind;
	}

	@Override
	public List<eligEntityBinding> pending() {
	             List<coNoticeEntity>   co =        nRepo.findNoticesByStatus("PENDING") ; 
	                           List<eligEntityBinding>  PendingList = new ArrayList<> () ;
	                            
	                         for(coNoticeEntity notice :co) {
	                        	
	                        	  entityEligibility elig = elRepo.findByCaseNum(notice.getCaseNum());
	                        	  eligEntityBinding bind = new eligEntityBinding();
	                        	  BeanUtils.copyProperties(elig, bind);
	                        	   PendingList.add(bind);
	                         }
		return PendingList;
	}
	
	@Override
	public boolean pdfGenerator(HttpServletResponse resp,Integer caseNum)throws Exception {
	                               appEntity app =               appRepo.findById(caseNum).get();
		 File f = new File("notice.xls");
	 boolean b = pdfGen.generateNoticePdf(resp, caseNum, f);
	 String subject = "DEPARTMENT OF HEALTH AND SERVICES";
	   String body = "<h1>IES ELIGIBILITY REPORT </h1>";
	   String to  = app.getEmail();
	mailSender.sendEmail(subject, body, to, f);
	f.delete();
	      coNoticeEntity notice =         nRepo.findBycaseNum(caseNum);
	       notice.setNoticeStatus("PRINTED");
	       nRepo.save(notice);
	       notice.setNoticePrintDate(LocalDate.now());
	       
			  return b;
	 		
	}

	
                 
}
