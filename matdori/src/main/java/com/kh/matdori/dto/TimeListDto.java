package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TimeListDto {
	private int clock2No;
	private String clock2Time;
    private int workdayNo;
    private String workdayName;
	private String notWorkday;
    private int resNo;
}