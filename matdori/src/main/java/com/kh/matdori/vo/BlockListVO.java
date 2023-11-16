package com.kh.matdori.vo;

public class BlockListVO {

	   private int currentPage;    // 현재 페이지 번호
	    private int itemsPerPage;   // 페이지당 항목 수
	    private int totalItems;      // 전체 항목 수
	    private String type;         // 검색 유형
	    private String keyword;      // 검색어

	    // 생성자, getter, setter 등을 구현해야 합니다.

	    // ... 생성자, getter, setter 등 생략 ...

	    // 시작 인덱스 계산
	    public int getStartIndex() {
	        return (currentPage - 1) * itemsPerPage;
	    }

	    // 종료 인덱스 계산
	    public int getEndIndex() {
	        return Math.min(currentPage * itemsPerPage, totalItems);
	    }

	    // 현재 페이지의 쿼리 스트링 생성
	    public String getQueryString(int page) {
	        return "type=" + type + "&keyword=" + keyword + "&page=" + page;
	    }

	    // 이전 페이지 쿼리 스트링 생성
	    public String getPrevQueryString() {
	        return "type=" + type + "&keyword=" + keyword + "&page=" + (currentPage - 1);
	    }

	    // 다음 페이지 쿼리 스트링 생성
	    public String getNextQueryString() {
	        return "type=" + type + "&keyword=" + keyword + "&page=" + (currentPage + 1);
	    }

	    // ... 필요한 다른 메서드 및 속성을 추가할 수 있습니다.
	}

