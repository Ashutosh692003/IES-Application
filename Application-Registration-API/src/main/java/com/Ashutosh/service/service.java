package com.Ashutosh.service;

import java.util.List;

import com.Ashutosh.binding.appEntityBinding;
import com.Ashutosh.entity.appEntity;

public interface service {
          public boolean register(appEntityBinding appData);
          
          public List<appEntityBinding> viewAll(Integer uid);
          
}
