package com.kh.matdori.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class CusAdminVO {

	
	    // 이용자 정보
	    private String customerId; // 이용자 아이디 
	    private String customerName; // 이용자 이름 
	    private String customerStatus; // 이용자 상태 (차단상태 Y / N)
	    private Date customerBlockTime; // 이용자 차단 시각 
	    private String customerBlockComment; // 이용자 차단 사유 
	    private String customerBlock; // 차단 여부 
	    private Date customerBirth;
	    private int customerContact;
	    private Integer begin;
	    private Integer end;

	    
	    private String type, keyword;	//검색 분류 및 키워드
		private int page = 1;//현재 페이지 번호(기본:1)
		private int size = 10;//보여줄 게시판의 글 수(기본:10)
		private int count;//전체 글 수
		private int navigatorSize = 10;//하단 네비게이터 표시 개수(기본:10)
		
		public boolean isSearch() {
			return type != null && keyword != null;
		}
		public int getBegin() {
			return (page-1) / navigatorSize * navigatorSize + 1;
		}
		public int getEnd() {
			int end = getBegin() + navigatorSize - 1;
			return Math.min(getPageCount(), end); 
		}
		public boolean isFirst() {
			return getBegin() == 1;
		}
		
		public int getPageCount() {
			return (count-1) / size + 1;
		}
		
		public boolean isLast() {
			return getEnd() >= getPageCount();
		}
		
		public String getPrevQueryString() {
			if(isSearch()) {//검색
				return "page="+(getBegin()-1)+"&size="+size+"&type="+type+"&keyword="+keyword;
			}
			else {//목록
				return "page="+(getBegin()-1)+"&size="+size;
			}
		}
		
		public String getNextQueryString() {
			if(isSearch()) {//검색
				return "page="+(getEnd()+1)+"&size="+size+"&type="+type+"&keyword="+keyword;
			}
			else {//목록
				return "page="+(getEnd()+1)+"&size="+size;
			}
		}
		
		public String getQueryString(int page) {
			if(isSearch()) {//검색
				return "page="+page+"&size="+size+"&type="+type+"&keyword="+keyword;
			}
			else {//목록
				return "page="+page+"&size="+size;
			}
		}
		
		public int getStartRow() {
			return getFinishRow() - (size-1);
		}
		public int getFinishRow() {
			return page * size;
		}
	}



