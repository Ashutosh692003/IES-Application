package com.Ashutosh.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "DC_CHILDREN")
public class childEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer childId;
    private String name;
    private  LocalDate dob;
    private  Long ssn;
 @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="Case_Number")
    private appEntity caseNum;
 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="user_id")
	 private userEntity user;
    
}
