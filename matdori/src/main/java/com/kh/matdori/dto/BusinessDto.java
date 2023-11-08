package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BusinessDto {
	private String busId, busPw;
	private String busRegNo;
	private String busName, busTel, busEmail;
	private String busPost, busAddr1, busAddr2;
	private Date busRegDate;

}
