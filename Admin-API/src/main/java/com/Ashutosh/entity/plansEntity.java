package com.Ashutosh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "IES_PLANS")
public class plansEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer	planId ;
private String	planName;
private LocalDate	planStartDate;
	private LocalDate planEndDate;
private String	planCategory;
private String actvieSt;
	@JsonIgnore
	  @OneToOne(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	   private userEntity createdBy;
	@JsonIgnore
	@OneToOne(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	private userEntity updatedBy ;
	  
	 @CreationTimestamp
	 @Column(updatable=false)
	private LocalDate createdDate;
	 @UpdateTimestamp
	 @Column(insertable=false)
	private LocalDate updatedDate;

}
