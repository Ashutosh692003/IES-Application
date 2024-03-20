package com.Ashutosh.service;

import java.util.List;

import com.Ashutosh.binding.dashBoardBinding;
import com.Ashutosh.binding.forgotBinding;
import com.Ashutosh.binding.loginBinding;
import com.Ashutosh.binding.unlockFormBinding;
import com.Ashutosh.binding.userBinding;
import com.Ashutosh.entity.userEntity;

public interface userService {
    public boolean createAcc(userBinding user);
    
    public String forgot(forgotBinding forgot);
    
    public List<userEntity> viewAccounts();
    
    public userEntity update(Integer userId);
    
    public String actvieStatus(Integer userId);
    
    public String login(loginBinding logCredentials);
    
    public  String readEmailBody(String filename,userEntity user);
    
    public String unlockAcc (unlockFormBinding data);
    
    public  dashBoardBinding getDashBoardData();
}

