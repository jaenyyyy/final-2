package com.kh.matdori.vo;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CusAdminVO {

	    // 주어진 JSP 코드에 사용된 변수 추가
	    private String type;
	    private String keyword;
	    private int page;
	    private boolean first;
	    private boolean last;
	    private String prevQueryString;
	    private String nextQueryString;
	    
	    // 이용자 정보
	    private String customerId; // 이용자 아이디 
	    private String customerStatus; // 이용자 상태 (차단상태 Y / N)
	    private Date customerBlockTime; // 이용자 차단 시각 
	    private String customerBlockComment; // 이용자 차단 사유 
	    private String customerBlock; // 차단 여부 

	    private Integer begin;
	    private Integer end;
	    private int size = 10; // 보여줄 게시판 글 수 
	    private int count; // 전체 글 수 
	    
	    
	

	   
	}



