package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationListDto {
	private int rezNo, resNo, menuNo, clockNo, seatNo;
	private String rezCustomerId;
	private String customerName;
	private String customerContact;
	private int customerPoint;
	private String customerLevel;
	private String resName, menuName;
	private Float menuPrice;
	private String clockSelect, seatName;
	private int rezMenuQty, rezCustomerCount, rezSeatQty;
	private String rezRequest;
//	private Date rezDay;
	


	
	
}
