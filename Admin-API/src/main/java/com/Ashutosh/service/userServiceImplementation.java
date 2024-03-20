package com.Ashutosh.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.App_Constants.appConstants;
import com.Ashutosh.binding.dashBoardBinding;
import com.Ashutosh.binding.forgotBinding;
import com.Ashutosh.binding.loginBinding;
import com.Ashutosh.binding.unlockFormBinding;
import com.Ashutosh.binding.userBinding;
import com.Ashutosh.entity.roleEntity;
import com.Ashutosh.entity.userEntity;
import com.Ashutosh.repo.roleRepo;
import com.Ashutosh.repo.userRepo;
import com.Ashutosh.utitlity.emailUtils;
import com.Ashutosh.utitlity.pwdProvider;
@Service
public class userServiceImplementation implements userService {
	
	 
	@Autowired
	userRepo uRepo;
	
	@Autowired
	 roleRepo rRepo;
	
	@Autowired
	 emailUtils mail;
                   
	 // method realted to create acc 
	   public boolean createAcc(userBinding us) {
		              String email = us.getUserEmail();
		         userEntity acc =          uRepo.findByuserEmail(email);
		           if(acc!=null) {
		        	 
		        	    return false;
		           }
		                        
		      userEntity user = new userEntity();
		        BeanUtils.copyProperties(us, user);
		               String roleF =        us.getRole() ;
		                  System.out.println(roleF);
		                          if(roleF=="ADMIN") {
		                       roleEntity role =  	     rRepo.findByRole(roleF);
		                            user.setRoleId(role);
		                          }
		                          else {
		                        	  roleEntity role =  	     rRepo.findByRole(roleF);
		                        	   user.setRoleId(role);
		                          }
		                       
		         pwdProvider pw = new pwdProvider();
		              String pwd =   pw.randomPGenerator();
		           user.setActiveSt("Y");
		           user.setAccStatus("LOCKED");
		           user.setUserPwd(pwd);
		           
		           
		            if(us.getUserDOB()!=null && us.getUserDOB().isEmpty()==false) {
		            	String dob = us.getUserDOB();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            LocalDate localDate = LocalDate.parse(dob, formatter);
		        
		                user.setUserDob(localDate);
		            }
		            
		            uRepo.save(user);
		           String to = user.getUserEmail();
				     String subject = "Unlock Your Account  |-DHS";
				   
		         String body = readEmailBody("Reg_email_body.txt",user);
		           mail.sendMail(subject, body.toString(), to);
		           
		           
		                       
		           
		           
		       return true;
		      
		      
	   }

	@Override
	public String forgot(forgotBinding forgot) {
		          String email = forgot.getEmail();
		       userEntity user =              uRepo.findByuserEmail(email);
		                   if(user==null) {
		                	    return "Account not exist";
		                   }
		                   
		                 
		                	             String pwd = user.getUserPwd();
		                	           String to  = user.getUserEmail();
		                	           String subject = "Password-Recovery   (DHS)";
		                	         String body = readEmailBody("Forgot_password_email_body.txt",user);
		                	   	mail.sendMail(subject, body.toString(), to);
		                	       
		                   
		                   
		return "Password sended sucessfully";
	}

	@Override
	public List<userEntity> viewAccounts() {
		              List<userEntity>  users =         uRepo.findAll();
		                     return users;
		    //we have to return binding form not all data
		
	}

	@Override
	public userEntity update(Integer userId) {
	             Optional<userEntity>   us =   uRepo.findById(userId);
	          	                               userEntity user = us.get()  ;  
		return user;
		// return bindingform
	}

	@Override
	public String actvieStatus(Integer userId) {
		Optional<userEntity>   us =   uRepo.findById(userId);
          userEntity user = us.get()  ;  
           
                                if(user.getActiveSt().equals("Y")) {
                                	
                                	 user.setActiveSt("N");
                                	 uRepo.save(user);
                                	 return "Account Deactivated Sucessfully";
                                }
                                else {
                                	user.setActiveSt("Y");
                                	 uRepo.save(user);
                               	 return "Account Activated Sucessfully";
                                }
		
	}

	@Override
	public String login(loginBinding logCredentials) {
		String email = logCredentials.getEmail();
		              userEntity user =               uRepo.findByuserEmail(email);
		                               if(user==null) {
		                            	    return appConstants.acc_not_exist;
		                               }
		                               if(user.getAccStatus().equals("LOCKED")) {
		                            	     return appConstants.unlock_acc;
		                               }
		                     String pwd = logCredentials.getPassword();
		                    String pwdOriginal = user.getUserPwd();
		                    
		                    if(pwd.equals(pwdOriginal)) {
		                    	 return appConstants.login_success;
		                    }
		return appConstants.incorrect_pwd;
	}

	@Override
	public String readEmailBody(String filename, userEntity user) {
		 StringBuilder sb = new StringBuilder();
		 try(Stream<String> lines = Files.lines(Paths.get(filename));) {
			lines.forEach(line->{
			line = line.replace("${FNAME}", user.getFullName());
			line = line.replace("${TEMP_PWD}", user.getUserPwd());
			line = line.replace("${EMAIL}", user.getUserEmail());
			sb.append(line);
			});
		
		 
		 
		 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String unlockAcc(unlockFormBinding data) {
		                 if(!data.getNewPwd().equals(data.getConfPwd())) {
		                	return appConstants.new_conf_not_matching ;
		                 }
		 String email = data.getEmail();
		userEntity user = uRepo.findByuserEmail(email);
		    
		      String userTpw = data.getTempPwd();
		      String dbTpw = user.getUserPwd(); 
		      
		       if(userTpw.equals(dbTpw)) {
		    	  
		    	      user.setUserPwd(data.getNewPwd());
		    	      user.setAccStatus("UNLOCK");
		    	      uRepo.save(user);
		    	      return appConstants.unlock_success;
		       }
		       
		return appConstants.wrong_temp;
	}

	@Override
	public dashBoardBinding getDashBoardData() {
		 dashBoardBinding  db = new dashBoardBinding();
		 
		return  db;
	}
	
	      
	 
	
	
	
	//unlock user based on email; retrun string
	
	// dashboard method
	//to load card data elig table required
}

      

