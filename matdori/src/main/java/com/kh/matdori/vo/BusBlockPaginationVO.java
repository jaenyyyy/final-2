package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class BusBlockPaginationVO {
    private String busId;
    private String busName;
    private String busBlockStatus;

	private int page = 1; //현재 페이지 번호  private in page = 1;  < 이런식으로 페이지 지정이 가능하다 (기본 : 1)
	private int size = 10; //보여줄 게시판의 글 수 (기본 : 10)
	private int count; //전체 글 수 
	private int navigatorSize = 10; //하단 네비게이터 표시 개수(기본 : 10)

    // 해당 메서드를 추가하여 필요한 쿼리스트링을 생성합니다.
    public String getQueryString(Integer pageNumber) {
        StringBuilder queryString = new StringBuilder();

        if (busId != null) {
            queryString.append("busId=").append(busId);
        }

        if (busName != null) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append("busName=").append(busName);
        }

        if (busBlockStatus != null) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append("busBlockStatus=").append(busBlockStatus);
        }

        return queryString.toString();
    }

    public void setPage(Integer pageNumber) {
        // 만약 전달된 pageNumber가 null이 아니라면 해당 값을 현재 페이지로 설정합니다.
        if (pageNumber != null) {
            this.page = pageNumber;
            System.out.println("Page number set to: " + pageNumber);
        }
    }


    public int getBegin() {
        return (page - 1) / navigatorSize * navigatorSize + 1;
    }

    public int getEnd() {
        int end = getBegin() + navigatorSize - 1;
        return Math.min(getPageCount(), end);
    }

    public boolean isFirst() {
        return getBegin() == 1;
    }

    public int getPageCount() {
        return (count - 1) / size + 1;
    }

    public boolean isLast() {
        return getEnd() >= getPageCount();
    }

    public int getStartRow() {
        return getFinishRow() - (size - 1);
    }

    public int getFinishRow() {
        return page * size;
    }

    public void calculatePageInfo() {
        int pageCount = (count - 1) / size + 1;
        if (page > pageCount) {
            page = pageCount;
        }
    }
}
