package com.kh.matdori.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ResAdminVO {
	private Integer resNo; //매장 번호
	private Integer resRegNo; //매장 사업자 등록 번호
	private String resName; //매장 이름
	private Date resRegDate; //매장 신청일
	private String resBlock;  //차단여부
	private String resJudgeStatus;  //매장 심사 상태
	private Integer begin;
	private Integer end;
}
