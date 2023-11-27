package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuInfoVO {
	private int menuNo;
	private int resNo;
	private String menuName;
	private int menuQty;
	private float menuPrice;

}
