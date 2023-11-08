package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.NoticeDto;

public interface NoticeDao {
	void insert(NoticeDto noticeDto); //등록
	List<NoticeDto> selectList(); //조회
	NoticeDto selectOne(int noticeNo); //상세
	void edit(int noticeNo, NoticeDto noticeDto); //수정
	void delete(int noticeNo); //삭제 
	
	List<NoticeDto> searchList(String noticeTitle); //검색
	List<NoticeDto> selectListByPage(int page, int size); //페이징
	
	
}
