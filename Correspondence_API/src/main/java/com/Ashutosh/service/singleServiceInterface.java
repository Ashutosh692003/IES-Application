package com.Ashutosh.service;

import java.util.List;

import com.Ashutosh.binding.eligEntityBinding;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

public interface singleServiceInterface { 
	
	 public eligEntityBinding viewCorrespondece(Integer caseNum);
	 
	 public List<eligEntityBinding> pending();
	 


	boolean pdfGenerator(HttpServletResponse resp, Integer caseNum) throws Exception;
		 
	 

}