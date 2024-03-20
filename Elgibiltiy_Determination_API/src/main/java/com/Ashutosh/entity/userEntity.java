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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="IES_USER ")
public class userEntity {
	@Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer 	userId	;
	private String fullName;
	private String userEmail;
	private String userPwd;
	private String userPhno;
	private String userGender;
	private LocalDate userDob;
	  private Integer userSsn;
private String 	activeSt ;
	private String accStatus;
	 @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private rolesEntity roleId;
	  
	 @CreationTimestamp
	 @Column(updatable=false)
	private LocalDate createDate;
	 @UpdateTimestamp
	 @Column(insertable=false)
	private LocalDate updateDate;
	
}
