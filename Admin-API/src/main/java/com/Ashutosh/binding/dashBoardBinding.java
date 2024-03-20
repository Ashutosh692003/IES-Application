package com.Ashutosh.binding;

import com.Ashutosh.entity.userEntity;

import lombok.Data;
@Data
public class dashBoardBinding {
        private Long planCnt;
        private Long approvedCnt;
        private Long denialCnt;
        private Long benifitAmt;
        private userBinding user;
}
