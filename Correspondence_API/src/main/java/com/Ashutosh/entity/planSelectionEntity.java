package com.Ashutosh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
