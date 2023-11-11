package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.QnaDto;
import com.kh.matdori.vo.PaginationVO;

public interface QnaDao {
	
	int sequence();
	void insert(QnaDto qnaDto); //등록
	List<QnaDto> selectList(PaginationVO vo); //조회
	QnaDto selectOne(int qnaNo); //상세
	boolean edit(QnaDto qnaDto); //수정
	boolean delete(int qnaNo); //삭제 
	
	int countList(PaginationVO vo); //검색+페이지네이션
}
