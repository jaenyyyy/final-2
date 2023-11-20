package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerAdminListDto {
	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerEmail;
	private int customerContact;
	private Date customerBirth;
	private String customerGender;
	private String customerLevel;
	private int customerPoint;
	private Date customerBlockTime;
	private String customerBlockComment;
	private String customerStatus;
	private String customerBlock;
}
