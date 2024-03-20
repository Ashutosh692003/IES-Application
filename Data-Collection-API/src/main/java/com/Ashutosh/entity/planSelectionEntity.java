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
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "DC_PLAN_SELECTION")
public class planSelectionEntity {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer planSelctionId;
	 private Integer  caseNum;
	 private Integer  planId;
	 @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 @JoinColumn(name="user_id")
	 private userEntity user;

}
