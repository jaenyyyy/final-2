package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatListByResVO {
	private int resNo, seatNo, seatResNo;
	private String resName;
	private String seatName;
	private int seatCount;
}
