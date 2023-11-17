package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerBlockDto {
	

	private String customerId; // 이용자 아이디 
	private String customerStatus; // 이용자 상태 (차단상태 Y / N)
	private Date customerBlockTime; // 이용자 차단 시각 
	private String customerBlockComment; // 이용자 차단 사유 
}