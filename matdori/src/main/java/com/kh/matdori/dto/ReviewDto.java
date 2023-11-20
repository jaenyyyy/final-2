package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewDto {
	private int reviewNo;
	private int resNo;
	private String reviewWriter;
	private String reviewContent;
	private Date reviewWriteDate;
	private int reviewStarPoint;
}
