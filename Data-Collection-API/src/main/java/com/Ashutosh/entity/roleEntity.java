package com.Ashutosh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "IES_USER_ROLES")
public class roleEntity {
	@Id
	 
	private Integer id;
	private String role;
}