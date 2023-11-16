package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuTypeDto {

	private int menuTypeNo;
	private int resNo;
	private String menuTypeName;
}
