package com.Ashutosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.binding.eligEntityBinding;
import com.Ashutosh.service.singleServiceInterface;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class controller {
                                 
	                      @Autowired
	                      singleServiceInterface single;
	                      
	                      
	      @GetMapping("/viewCorrespondence/{caseNum}")
	public eligEntityBinding viewCorrespondence(@PathVariable("caseNum")Integer caseNum){
	    	  
	                eligEntityBinding elig = single.viewCorrespondece(caseNum);
	                return elig;
	                	
	}    
	            @GetMapping("/pending")
	      public List<eligEntityBinding>  pendingNotice(){
	    	  List<eligEntityBinding> pending =               single.pending();
	    	   return pending;
	      }
	                  
	      
	      // view determination by caseNum service can be given by viewCorrespondence
	      
	      @GetMapping("/generateNotice/{caseNum}")
	       public ResponseEntity<String> generateEntity(HttpServletResponse resp,@PathVariable("caseNum")Integer caseNum) throws Exception{
	    	  resp.setContentType("application/pdf");
		    	
	    	  try{resp.addHeader("Content-Disposition", "attachment;filename=Notice.pdf");
	    	boolean b =        single.pdfGenerator(resp, caseNum);
	    	   if(b) {
	    		   return ResponseEntity.ok("Notice Generated Successfully"); 
	    	   }
	    	   else {
	    		   throw new Exception("PDF generation failed .");
	    	   }}
	    	   catch(Exception e) {
	    		   e.printStackTrace();
	               resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());  
	    	   }	   
	      }
	          
	        
	  
} 
