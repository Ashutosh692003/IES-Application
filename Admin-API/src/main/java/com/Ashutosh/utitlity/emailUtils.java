package com.Ashutosh.utitlity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class emailUtils {
	    @Autowired
	  JavaMailSender jsender;
	 
	  public boolean sendMail(String subject,String body,String to) {
		        MimeMessage mail=      jsender.createMimeMessage();
		         MimeMessageHelper mHelper;
				try {
					mHelper = new MimeMessageHelper(mail,true);
					 mHelper.setSubject(subject);
			           mHelper.setText(body, true);
			           mHelper.setTo(to);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
		          
		           
		           jsender.send(mail);
		     return true;
	  }
}
