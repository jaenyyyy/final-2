package com.kh.matdori.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationDto {
	private int rezNo;
	private String rezCustomerId;
	private int rezResNo;
	private int rezClockNo, rezSeatNo;
	private int rezCustomerCount, rezMenuQty, rezSeatQty;
	private String rezRequest;
	private String rezStatus;
	
	private List<MenuDto> menuList;
}
