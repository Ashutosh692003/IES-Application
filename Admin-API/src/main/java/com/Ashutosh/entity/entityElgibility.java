package com.Ashutosh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ED_ELIG_DTLS")
public class entityElgibility {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer   ed_trace_id;
  private String planName;
  private String planStatus;
  private LocalDate eligStartDate;
  private LocalDate eligEndDate;
  private Integer  benifitAmt;
  private String denialReason;
   @CreationTimestamp
   
  private LocalDate createDate;
}
