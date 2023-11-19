package com.kh.matdori.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaoPayReadyResponseVO {
	private String tid;  //결제 고유 번호
	private String nextRedirectAppUrl; //모바일 앱 용 결제 페이지 주소
	private String nextRedirectMobileUrl; //모바일 웹 용 결제 페이지 주소
	private String nextRedirectPcUrl; //PC 웹용 결제 페이지 주
	private String androidAppScheme;  //카카오페이 결제를 위한 안드로이드 스킴
	private String iosAppScheme; //카카오페이 결제를 위한 아이폰 스킴
	private Date createdAt; // 결제 준비를 요청한 시간
	
	
}
