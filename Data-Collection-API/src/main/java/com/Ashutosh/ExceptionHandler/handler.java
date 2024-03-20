package com.Ashutosh.ExceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class handler {
                    @ExceptionHandler(value = Exception.class)
	  public ResponseEntity<format> handleMethod(Exception e){
		               format exc = new format();
		               String msg = e.getMessage();
		               String code = "DC-101";
		              exc.setCode(code);
		              exc.setMsg(msg);
		              exc.setTime(LocalDateTime.now());
		              
		            return new ResponseEntity<>	(exc,HttpStatus.INTERNAL_SERVER_ERROR) ;
		            }
}
