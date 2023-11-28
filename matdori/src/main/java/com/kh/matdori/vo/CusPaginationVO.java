package com.kh.matdori.vo;

import lombok.Data;

@Data
public class CusPaginationVO {
    private String keyword;
    private int page = 1;
    private int size = 5;
    private int count;
    private int navigatorSize = 10;
    private String rezCustomerId;

    public boolean isSearch() {
        return keyword != null;
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

    public String getPrevQueryString() {
        if (isSearch()) {
            return "page=" + (getBegin() - 1) + "&keyword=" + keyword;
        } else {
            return "page=" + (getBegin() - 1);
        }
    }

    public String getNextQueryString() {
        if (isSearch()) {
            return "page=" + (getEnd() + 1) + "&size=" + size + "&keyword=" + keyword;
        } else {
            return "page=" + (getEnd() + 1) + "&size=" + size;
        }
    }

    public String getQueryString(int page) {
        if (isSearch()) {
            return "page=" + page + "&size=" + size + "&keyword=" + keyword;
        } else {
            return "page=" + page + "&size=" + size;
        }
    }

    public int getStartRow() {
        return (page - 1) * size;
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
