package com.kh.matdori.vo;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.RestaurantJudgeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantJudgeVO {
	private String busId;
	private int resRegionNo;
	private String resRegNo;
	private String resName;
	private String resTel;
	private String resPost, resAddr1, resAddr2;
	private Timestamp resRegDate;
	
	@JsonIgnore
	private int resNo;
	@JsonIgnore
	private int resJudgeNo;
	
	
//	private int resJudgeNo;
//	private String resJudgeStatus;
//	private Date resJudgeDate;
//	private String resJudgeComment;
}