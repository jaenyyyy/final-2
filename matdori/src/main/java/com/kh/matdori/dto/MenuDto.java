package com.kh.matdori.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuDto {
	private int menuNo;
	private int menuTypeNo;
	private int resNo;
	private String menuName;
	private int menuPrice;
	private String menuContent;
}
