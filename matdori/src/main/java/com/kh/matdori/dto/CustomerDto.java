package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerDto {

	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerEmail;
	private String customerContact;
	private String customerBirth;
	private String customerGender;
	private String customerLevel;
	private int customerPoint;
}
