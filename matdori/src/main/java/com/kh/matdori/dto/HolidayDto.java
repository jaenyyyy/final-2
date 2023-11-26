package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class HolidayDto {

	private int holidayNo;
	private String holidayBegin;
	private String holidayEnd;
	private String holidayName;
	private int holiResNo;
}
