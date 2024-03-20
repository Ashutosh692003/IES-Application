package com.Ashutosh.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class viewPlanBinding {
   private String planName;
   private LocalDate planStartDate;
   private LocalDate planEndDate;
   private String planCategory;
   private String  actvieSt;
}
