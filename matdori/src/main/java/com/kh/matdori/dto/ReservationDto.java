package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationDto {
	private int rezNo;
	private String rezCustomerId;
	private int rezResNo;
	private int rezMenuNo;
	private OkDto okDto;//활용하자
	
	private int rezCustomerCount;
	private String rezRequest;
	private String rezStatus;
}
