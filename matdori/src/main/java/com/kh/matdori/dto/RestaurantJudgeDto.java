package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantJudgeDto {
	private int resJudgeNo;
	private int resNo;
	private String resJudgeStatus;
	private Date resJudgeDate;
	private String resJudgeComment;
}
