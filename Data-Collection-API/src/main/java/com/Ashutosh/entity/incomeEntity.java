package com.Ashutosh.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = " DC_INCOME")
public class incomeEntity {
	               @Id
	               @GeneratedValue(strategy =GenerationType.IDENTITY)
          private Integer  incomeId;
          private String salary;
          private String rentIncome;
          private String propertyIncome;
              @OneToOne(fetch  = FetchType.EAGER)
          private appEntity caseNum;
              @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
         	 @JoinColumn(name="user_id")
         	 private userEntity user;
}
