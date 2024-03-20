package com.Ashutosh.service;

import java.util.List;

import com.Ashutosh.binding.reportBinding;
import com.Ashutosh.binding.responseBinding;

public interface serviceInterface {
            public List<responseBinding> getReports(reportBinding reportCriteria);
            
            
}
