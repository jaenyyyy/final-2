package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationListDto {
	private int rezNo, resNo, menuNo, clockNo, seatNo;
	private String customerId;
	private String resName, menuName;
	private Float menuPrice;
	private String clockSelect, seatName;
	private int rezMenuQty, rezCustomerCount, rezSeatQty;
	private String rezRequest;
//	private Date rezDay;
	
//	private List<MenuDto> menuList;
	public Float getTotal() {
		return menuPrice * rezMenuQty;
	}
}
