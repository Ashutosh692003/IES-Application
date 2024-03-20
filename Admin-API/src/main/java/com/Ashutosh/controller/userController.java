package com.Ashutosh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.App_Constants.appConstants;
import com.Ashutosh.binding.dashBoardBinding;
import com.Ashutosh.binding.forgotBinding;
import com.Ashutosh.binding.loginBinding;
import com.Ashutosh.binding.unlockFormBinding;
import com.Ashutosh.binding.userBinding;
import com.Ashutosh.entity.userEntity;
import com.Ashutosh.repo.userRepo;
import com.Ashutosh.service.userService;
import com.Ashutosh.service.userServiceImplementation;

@RestController
public class userController {
  
	 @Autowired
	  userService userServiceImpl;
	 // use responseEntity 
	 
	 @Autowired
	 userRepo uRepo;
	 
	 
	  Logger log  = LoggerFactory.getLogger(userController.class);
	 
	      @PostMapping("/createAcc")
	  public ResponseEntity<String>signUp(@RequestBody userBinding user) {
		    boolean b =             userServiceImpl.createAcc(user);
		     log.debug("Account Creation Started....");
		
		     if(b==true) {
		    	 
		    	 log.info("Account Created Successfully");
		    	 return new ResponseEntity<String>(appConstants.acc_createed,HttpStatus.CREATED); 
		     }
		     else {
		    	   log.info("Account Creation Unsuccesfull");
		    	   return new ResponseEntity<String>(appConstants.problem,HttpStatus.INTERNAL_SERVER_ERROR);
		     }
		      
	  }
	 
	       @PostMapping("/unlockAcc")
	      public ResponseEntity<String> unlockAcc(@RequestBody unlockFormBinding data){
	    	     log.debug("unlock account process started");
	    	    String resp =      userServiceImpl.unlockAcc(data);
	    	    log.info("Account unlock process ended");
	    	     return new ResponseEntity<>(resp,HttpStatus.OK);
	      }
	      
	      
	      
	      
	      
	      
	        @PostMapping("/forgot")
	       public ResponseEntity<String>forgot(@RequestBody forgotBinding forgot) {
	        	  log.debug("Forgot password process started");
	        	 String s = userServiceImpl.forgot(forgot);
	        	          if(s.contains("Password")) {
	        	       log.info("password recovered sucessfully");
	        	        	  return new ResponseEntity<String>(appConstants.pwd_recover,HttpStatus.OK);
	        	        
	        	          }
	        	          else {
	        	        	   log.info("Wrong forgot request");
	        	        	  return new ResponseEntity<String>(appConstants.acc_not_exist,HttpStatus.BAD_REQUEST);  
	        	          }
	         }
	        
	        
	        @GetMapping("/viewAccounts")
	       public ResponseEntity<List<userEntity>>  viewAcc(){
	    	List<userEntity>  acc =          userServiceImpl.viewAccounts()   ;
	    	 return new ResponseEntity<>(acc,HttpStatus.OK);
	       }
	              
	         @GetMapping("/update")
	       public ResponseEntity<userEntity >update(@RequestParam("userId") Integer uderId ) {
	        	   userEntity user =        userServiceImpl.update(uderId);
	        	   return new ResponseEntity<>(user,HttpStatus.OK);
	         }
	         
	             @GetMapping("/activeStatus")
	         public ResponseEntity< String >activeStatus(@RequestParam("userId") Integer userId) {
	        	    String str =            userServiceImpl.actvieStatus(userId);
	        	    return new ResponseEntity<>(str,HttpStatus.OK);
	         }
	              @PostMapping("/login")
	             public String login(@RequestBody loginBinding logCredentials ) {
	            	     String b =            userServiceImpl.login(logCredentials);
	            	            if(b.equals("Success")) {
	            	            	return "redirect:/dashboard?email=" + logCredentials.getPassword();
	            	            }
	            	         return b;
	            	 
	             }
	               @GetMapping("/dashboard")
	              public dashBoardBinding  dashboard(@RequestParam("email")String email) {
	            	      userEntity user =         uRepo.findByuserEmail(email);
	            	       userBinding usData = new userBinding();
	            	      BeanUtils.copyProperties(user, usData);
	           dashBoardBinding db =  	      userServiceImpl.getDashBoardData();
	                   db.setUser(usData);
	                      return db;
	            	 
	            	              
	              }
	              
	         
	              // dashboard
	              // login redirect url to dashboard
	 // do logging
}
