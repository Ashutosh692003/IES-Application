package com.Ashutosh.ExceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionTaker {
	 @ExceptionHandler(value = Exception.class)
         public ResponseEntity<ExceptionFormat> exceptionTaker(Exception e){
		   
		          String message = e.getMessage();
		          String code = "AR-001";
		          ExceptionFormat  format  = new ExceptionFormat();
		            format.setMsg(message);
		            format.setCode(code);
		            format.setTime(LocalDateTime.now());
		            
		            return new ResponseEntity<>(format,HttpStatus.INTERNAL_SERVER_ERROR);
		          
		  
        	  
         }
}
