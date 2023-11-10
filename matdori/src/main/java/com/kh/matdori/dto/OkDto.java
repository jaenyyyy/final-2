package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OkDto {
	private int okNo;
	private int clockNo;
	private int seatNo;
	private String okStatus;

}
