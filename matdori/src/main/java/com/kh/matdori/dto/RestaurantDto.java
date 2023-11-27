package com.kh.matdori.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder @Repository
public class RestaurantDto {
	private int resNo;
	private String busId;
	private int resRegionNo;
	private String resRegNo;
	private String resName;
	private String resTel;
	private String resOpenTime;
	private String resPost, resAddr1, resAddr2;
	private Timestamp resRegDate;
	private String resNotice;
	
}
