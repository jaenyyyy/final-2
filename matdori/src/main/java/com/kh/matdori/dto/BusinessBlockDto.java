package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class BusinessBlockDto {
	
	private String busId; //사업자 아이디
	private Date busBlockTime;//사업자 차단시간
	private String busBlockComment;//사업자 차단 코멘트(사유)
	private String busBlockStatus;//사업자 차단 상태(기본은  N 차단은 Y)

}
