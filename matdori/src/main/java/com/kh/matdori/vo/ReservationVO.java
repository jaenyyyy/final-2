package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationVO {
	private int rezNo;
	private String customerId;
	private String resName, menuName;
	private int menuPrice;
	private String clockSelect, seatName;
	private int rezMenuQty, rezCustomerCount, rezSeatQty;
//	private Date rezDay;
	
//	private List<MenuDto> menuList;
	public int getTotal() {
		return menuPrice * rezMenuQty;
	}
}
