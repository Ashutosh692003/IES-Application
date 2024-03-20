package com.Ashutosh.service;

import com.Ashutosh.binding.eligEntityBinding;
 
public interface serviceInterface {
        public eligEntityBinding eligDetermin(Integer caseNum);
        
        public String generateCorrespondence(Integer caseNum);
}
