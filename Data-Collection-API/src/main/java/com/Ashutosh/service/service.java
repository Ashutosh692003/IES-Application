package com.Ashutosh.service;

import java.util.List;
import java.util.Map;

import com.Ashutosh.binding.childEntityBinding;
import com.Ashutosh.binding.childListBinding;
import com.Ashutosh.binding.educationEntityBinding;
import com.Ashutosh.binding.incomeEntityBinding;
import com.Ashutosh.binding.planselectionBinding;
import com.Ashutosh.binding.summaryBinding;
import com.Ashutosh.entity.educationEntity;

public interface service {
    public  boolean planSelection(planselectionBinding planSelectData);
     
    public boolean saveIncome(incomeEntityBinding incomeData);
              
    public boolean  saveEducationDetails(educationEntityBinding eduData);
    
    public boolean saveChildData(childListBinding childData);
    
    public Map<Integer,String> planDetails();
     
     public summaryBinding summary(Integer caseNum);
    // plans availabel in system should give
    
    //summRY
}
