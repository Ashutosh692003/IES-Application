package com.Ashutosh.binding;

import lombok.Data;

@Data
public class unlockFormBinding {
      private String email;
      private String tempPwd;
      private String newPwd;
      private String confPwd;
}
