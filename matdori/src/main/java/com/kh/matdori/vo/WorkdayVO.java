package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkdayVO {
	private int rezNo, workdayNo;
	private int rezResNo, workdayResNo;
	private String inputDate, dayName;
	private String inputTime, notWorkday;

}
