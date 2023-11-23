package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantDetailVO {
	private int resNo;
	private String resName, resTel;
	private String resOpenTime;
	private String resPost, resAddr1, resAddr2;
//	private String resNotice;
	private String menuName, menuContent;
	private Float menuPrice;

}
