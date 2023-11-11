package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.NoticeDto;
import com.kh.matdori.vo.PaginationVO;

public interface NoticeDao {
	
	int sequence(); //시퀀스 등록
	void insert(NoticeDto noticeDto); //등록
	List<NoticeDto> selectList(PaginationVO vo); //조회
	NoticeDto selectOne(int noticeNo); //상세
	boolean edit(NoticeDto noticeDto); //수정
	boolean delete(int noticeNo); //삭제 
	
	int countList(PaginationVO vo); //검색 + 페이지네이션
	
}
