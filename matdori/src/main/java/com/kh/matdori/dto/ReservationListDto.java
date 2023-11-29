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
	private int  menuPrice;
	private String clockSelect, seatName;
	private int rezMenuQty, rezCustomerCount, rezSeatQty;
	private String rezRequest;
	private String paymentStatus;
	private int reviewRezNo;
	//private boolean reviewWritten; // 리뷰 작성 여부를 나타내는 필드 추가
//	private Date rezDay;
	
	
}

