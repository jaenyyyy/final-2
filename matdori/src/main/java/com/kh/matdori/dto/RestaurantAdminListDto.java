package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantAdminListDto {
	private String resNo;
	private int regNo;
	private String resName;
	private Date regDate;
}