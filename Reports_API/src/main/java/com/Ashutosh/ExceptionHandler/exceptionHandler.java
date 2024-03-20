package com.Ashutosh.ExceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class exceptionHandler {
	         
	     @ExceptionHandler(value = Exception.class)
     public ResponseEntity<format> exceptionHandler(Exception e){
    	  format detail  = new format();
    	    String message = e.getMessage();
    	     detail.setMsg(message);
    	     detail.setCode("ED101");
    	     detail.setData(LocalDateTime.now());
    	     
    	     return new ResponseEntity<>(detail,HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
