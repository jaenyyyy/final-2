package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatListDto {
	private int seat2No;
	private String seat2Name;
	private int seat2Count;
	private int resNo;

}
