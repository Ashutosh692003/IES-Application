package com.Ashutosh.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.Ashutosh.entity.appEntity;
import com.Ashutosh.entity.coNoticeEntity;
import com.Ashutosh.entity.entityEligibility;
import com.Ashutosh.repo.appEntityRepo;
import com.Ashutosh.repo.eligibilityRepo;
import com.Ashutosh.repo.noticeRepo;
import com.Ashutosh.utility.emailSender;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
@Service
public class batchService implements batchServiceInterface {
	                                       @Autowired
	                                       noticeRepo nRepo;
	                                       
	                                  	 @Autowired
	                                  	 eligibilityRepo elRepo;
	                                  	 
	                                  	 @Autowired
	                                  	 appEntityRepo appRepo;
	                                  	 
	                                  	 @Autowired
	                                  	 emailSender mailSender;
	@Scheduled(cron="")
	@Override
	public void batch() {
		     
		List<coNoticeEntity> pendingNotices= nRepo.findNoticesByStatus("PENDING");
		ExecutorService pool =         Executors.newFixedThreadPool(5);
		for(coNoticeEntity entity : pendingNotices) {
			 pool.submit(new Runnable() {

				@Override
				public void run() {
					try {
						   printer(entity);
					}
					catch(Exception e) {
						 e.printStackTrace();
					}
					
				}
				 
			 }) ;   
		}
		
		
	}
	
	public void printer(coNoticeEntity entity) throws Exception {
		            
		              Integer caseNum = entity.getCaseNum() ;
		         generateNoticePdf(caseNum)	;                     
	}
	
	public void generateNoticePdf(Integer caseNum) throws Exception {
		
		File tempFile = File.createTempFile(caseNum.toString(),".pdf");
		 FileOutputStream outputStream = new FileOutputStream(tempFile);
		 
		 
	  
	    Document document = new Document(); // Use default page size
	  
	    PdfWriter.getInstance(document,outputStream);

	    document.open();

	    // Define Color Constants (adjust as needed)
	    Color titleColor = new Color(0, 32, 96); // Dark blue
	    Color nameColor = new Color(0, 0, 128); // Navy blue
	    Color bodyColor = new Color(64, 64, 64); // Dark gray
	    Color selectedColor = new Color(0, 128, 0); // Green
	    Color deniedColor = new Color(255, 0, 0); // Red

	    // Title
	    Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, titleColor);
	    Paragraph title = new Paragraph("Department of Health & Services - Notice", titleFont);
	    title.setAlignment(Paragraph.ALIGN_CENTER);
	    document.add(title);
	    
	        appEntity app  =        appRepo.findById(caseNum).get();
	        entityEligibility eligEntity = elRepo.findByCaseNum(caseNum);

	    // Loop through notices
	  
	        // Recipient Information
	        Paragraph recipientInfo = new Paragraph();
	        recipientInfo.add(new Phrase("Name: " + app.getName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, nameColor)));
	        recipientInfo.add(new Phrase("Address: " + app.getHouseNum(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, bodyColor)));
	        document.add(recipientInfo);

	        // Notice Body
	        Paragraph noticeBody = new Paragraph();
	        noticeBody.add(new Phrase("This is a notice from the Department of Health & Services regarding your application for the " + eligEntity.getPlanName() + " plan.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, bodyColor)));

	        // Plan Selection Outcome
	        if (eligEntity.getPlanStatus().equals("APPROVED")) {
	            noticeBody.add(new Phrase("\nCongratulations! Your application for the " + eligEntity.getPlanName() + " plan has been selected.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, selectedColor)));
	            noticeBody.add(new Phrase("\nPlan Start Date is  " + eligEntity.getEligStartDate() , FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, nameColor)));
	            noticeBody.add(new Phrase("\nPlan End   Date is  " + eligEntity.getEligEndDate() , FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, nameColor)));
	            noticeBody.add(new Phrase("\nAllocated Amount is   " + eligEntity.getBenifitAmt() , FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, deniedColor)));
	        } else if (eligEntity.getPlanStatus().equals("Denied")) {
	            noticeBody.add(new Phrase("\nWe regret to inform you that your application for the " + eligEntity.getPlanName() + " plan has not been selected this time.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, deniedColor)));
	            // Optionally, include denial reason
	            if (eligEntity.getDenialReason() != null && !eligEntity.getDenialReason().isEmpty()) {
	                noticeBody.add(new Phrase("\nReason for denial: " + eligEntity.getDenialReason(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
	            }
	        } 

	        document.add(noticeBody);

	        // Add a line break between notices
	      
	    

	    document.close();
	    String subject = "DEPARTMENT OF HEALTH AND SERVICES";
		   String body = "<h1>IES ELIGIBILTY REPORT </h1>";
		   String to  = app.getEmail();
                        
	          mailSender.sendEmail(subject, body, to, tempFile);
	          if (tempFile.exists()) {
	              tempFile.delete();
	          }
	    

	   
	}
	
 
}