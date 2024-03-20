package com.Ashutosh.Excpetion_Handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class handleException {
              @ExceptionHandler(Exception.class)
	      public ResponseEntity<exceptionFormat> handler(Exception e){
	    	            String messsage = e.getMessage();
	    	            exceptionFormat ex = new exceptionFormat();
	    	             ex.setMsg(messsage);
	    	             ex.setCode("err101");
	    	             ex.setTime(LocalDateTime.now());
	    	             
	    	      return new ResponseEntity<>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
	      }
}
