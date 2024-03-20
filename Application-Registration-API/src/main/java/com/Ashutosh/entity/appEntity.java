package com.Ashutosh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name= "App_Registration_Data")
public class appEntity {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer caseNum;
	   private String name;
	   private String email;
	   private LocalDate dob;
	   private String gender;
	   private Integer ssn;
	   private Long phNo;
	   private String cityName;
	   private String houseNum;
	   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	   @JoinColumn(name="user_id")
	   private userEntity createdBy;
	   @CreationTimestamp
	   private LocalDate createDate;
	   
	   
}
