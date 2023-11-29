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
	private int rezClockNo;
	private int rezSeatNo;
	private int rezCustomerCount, rezSeatQty;
	private String rezRequest;
	private String rezStatus;
//	private String inputDate;
//	private String inputTime;
	
//	private List<MenuWithImagesVO> rezMenuList;

	
	
}
