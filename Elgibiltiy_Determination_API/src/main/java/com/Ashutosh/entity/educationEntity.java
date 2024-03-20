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
@Table(name = " DC_EDUCATION")
public class educationEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer eudcationId;
	 private String highestDegree;
	 private Integer graduationYear;
	 private String university;
	 @OneToOne(fetch= FetchType.EAGER)
	 private appEntity caseNum;
	 @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 @JoinColumn(name="user_id")
	 private userEntity user;

}
