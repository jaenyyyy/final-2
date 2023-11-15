package com.kh.matdori.vo;

import java.util.List;

import com.kh.matdori.dto.MenuDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationVO {
	private int rezNo;
	private String customerId;
	private String resName, menuName;
	private Float menuPrice;
	private String clockSelect, seatName;
	private int rezMenuQty, rezCustomerCount, rezSeatQty;
//	private Date rezDay;
	
	private List<MenuDto> menuList;
	public Float getTotal() {
		return menuPrice * rezMenuQty;
	}
}
