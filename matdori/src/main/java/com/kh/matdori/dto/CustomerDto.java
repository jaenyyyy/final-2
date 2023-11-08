package com.kh.matdori.dto;

import lombok.Data;

@Data
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
