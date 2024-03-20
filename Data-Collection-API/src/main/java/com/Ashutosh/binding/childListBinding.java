package com.Ashutosh.binding;

import java.util.List;

import lombok.Data;

@Data
public class childListBinding {
        private List<childEntityBinding> childs;
        
        private Integer userId;
                   
        private Integer caseNumInt;
}
