package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatDto {
	private int seatNo;
	private int seatResNo;
	private String seatName;
	private int seatCount;
}
