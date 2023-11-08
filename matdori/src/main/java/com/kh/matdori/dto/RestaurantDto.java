package com.kh.matdori.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantDto {
	private int resNo;
	private String busId;
	private String resRegNo;
	private String resName;
	private String resTel;
	private String resPost, resAddr1, resAddr2;
	private Timestamp resRegDate;

}
