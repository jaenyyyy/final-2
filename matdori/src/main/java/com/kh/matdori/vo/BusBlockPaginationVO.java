/*
 * package com.kh.matdori.vo;
 * 
 * import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data;
 * import lombok.NoArgsConstructor;
 * 
 * @Data
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor public class BusBlockPaginationVO { private String busId;
 * private String busName; private String busBlockStatus;
 * 
 * private int page = 1; //현재 페이지 번호 private in page = 1; < 이런식으로 페이지 지정이 가능하다
 * (기본 : 1) private int size = 10; //보여줄 게시판의 글 수 (기본 : 10) private int count;
 * //전체 글 수 private int navigatorSize = 10; //하단 네비게이터 표시 개수(기본 : 10)
 * 
 * // 해당 메서드를 추가하여 필요한 쿼리스트링을 생성합니다. public String getQueryString(Integer
 * pageNumber) { StringBuilder queryString = new StringBuilder();
 * 
 * if (busId != null) { queryString.append("busId=").append(busId); }
 * 
 * if (busName != null) { if (queryString.length() > 0) {
 * queryString.append("&"); } queryString.append("busName=").append(busName); }
 * 
 * if (busBlockStatus != null) { if (queryString.length() > 0) {
 * queryString.append("&"); }
 * queryString.append("busBlockStatus=").append(busBlockStatus); }
 * 
 * return queryString.toString(); }
 * 
 * public void setPage(Integer pageNumber) { // 만약 전달된 pageNumber가 null이 아니라면 해당
 * 값을 현재 페이지로 설정합니다. if (pageNumber != null) { this.page = pageNumber;
 * System.out.println("Page number set to: " + pageNumber); } }
 * 
 * 
 * public int getBegin() { return (page - 1) / navigatorSize * navigatorSize +
 * 1; }
 * 
 * public int getEnd() { int end = getBegin() + navigatorSize - 1; return
 * Math.min(getPageCount(), end); }
 * 
 * public boolean isFirst() { return getBegin() == 1; }
 * 
 * public int getPageCount() { return (count - 1) / size + 1; }
 * 
 * public boolean isLast() { return getEnd() >= getPageCount(); }
 * 
 * public int getStartRow() { return getFinishRow() - (size - 1); }
 * 
 * public int getFinishRow() { return page * size; }
 * 
 * public void calculatePageInfo() { int pageCount = (count - 1) / size + 1; if
 * (page > pageCount) { page = pageCount; } } }
 */
package com.kh.matdori.vo;

import lombok.Data;

@Data
public class BusBlockPaginationVO {
	//검색 분류 및 키워드
	private String keyword; //검색 분류 및 키워드
	private int page = 1; //현재 페이지 번호  private in page = 1;  < 이런식으로 페이지 지정이 가능하다 (기본 : 1)
	private int size = 10; //보여줄 게시판의 글 수 (기본 : 10)
	private int count; //전체 글 수 
	private int navigatorSize = 10; //하단 네비게이터 표시 개수(기본 : 10)

	
	public boolean isSearch() {
		return keyword != null;
	}
	public int getBegin() { //첫 시작 번호를 알 수 있는 메소드 생성
		return (page-1)/navigatorSize*navigatorSize+1;
	}
	public int getEnd() { //끝 번호를 알 수 있는 메소드 생성
		int end = getBegin() + navigatorSize - 1;
		return Math.min(getPageCount(), end);
	}
	public boolean isFirst() {
		return getBegin() == 1;
	}
	public int getPageCount() {
		return (count-1) / size +1;
	}
	public boolean isLast() {
		return getEnd() >= getPageCount();
	}
	
	
	public String getPrevQueryString() {
		if(isSearch()) {  //검색이면
			return "page=" + (getBegin()-1) + "&keyword=" + keyword;
		}
		else { //목록이면
			return "page=" + (getBegin()-1);
		}
	}
	public String getNextQueryString() {
		if(isSearch()) {  //검색이면
			return "page=" + (getEnd()+1) + "&size=" + size + "&keyword=" + keyword;
		}
		else { //목록이면
			return "page=" + (getEnd()+1 + "&size=" + size);
		}
	}
	public String getQueryString(int page) {
		if(isSearch()) {  //검색이면
			return "page=" + (page) + "&size=" + size + "&keyword=" + keyword;
		}
		else { //목록이면
			return "page=" + page;
		}
	}
	
	
	public int getStartRow() {
		return getFinishRow() - (size-1);
	}
	public int getFinishRow() {
		return page * size;
	}
	
	
	public void calculatePageInfo() {
	    // 페이지 수 계산
	    int pageCount = (count - 1) / size + 1;
	    // 현재 페이지가 전체 페이지 수보다 크면 현재 페이지를 전체 페이지 수로 변경
	    if (page > pageCount) {
	        page = pageCount;
	    }
	}
}