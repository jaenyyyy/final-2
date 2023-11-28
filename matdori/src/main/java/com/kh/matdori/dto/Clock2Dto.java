package com.kh.matdori.dto;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Clock2Dto {
	private int clock2No;
	private int clock2ResNo;
	private String clock2Select;
	private String clock2EatingTime;
}

