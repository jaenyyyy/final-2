package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantAdminListDto {
	private int resNo; //매장 번호
	private int resRegNo; //매장 사업자 등록 번호
	private String resName; //매장 이름
	private Date resRegDate; //매장 신청일
	private String resBlock;  //차단여부
	private Date resBlockTime; //차단시간
	private String resBlockComment; //차단사유
	private String resJudgeStatus;  //매장 심사 상태
	private Date resJudgeDate; //심사시간
	private String resJudgeComment; //심사사유
}