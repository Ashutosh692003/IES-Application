package com.Ashutosh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "CO_NOTICES")
public class coNoticeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeId;

	private Integer caseNum;

	private String noticeStatus;

	private String noticeUrl;

	private Integer edgTraceId;

	private LocalDate noticePrintDate;
	
	
	
}
