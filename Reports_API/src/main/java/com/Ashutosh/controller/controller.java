package com.Ashutosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ashutosh.binding.reportBinding;
import com.Ashutosh.binding.responseBinding;
import com.Ashutosh.service.serviceInterface;

@RestController
public class controller { 
	     
	      @Autowired
	      serviceInterface service;
                   
	              @PostMapping("/getReport")
	            public List<responseBinding> getReport(reportBinding reportCriteria){
	            List<responseBinding>	 resp =               service.getReports(reportCriteria);
	               return resp;
	            	     
	            }
}
