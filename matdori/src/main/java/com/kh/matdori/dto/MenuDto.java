package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuDto {
	private int menuNo;
	private int resNo;
	private String menuName;
	private Float menuPrice;
	private String menuContent;
}
