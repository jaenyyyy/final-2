package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.QnaDto;

public interface QnaDao {
	void insert(QnaDto qnaDto); //등록
	List<QnaDto> selectList(); //조회
	QnaDto selectOne(int qnaNo); //상세
	void edit(int qnaNo, QnaDto qnaDto); //수정
	void delete(int qnaNo); //삭제 
	
	List<QnaDto> searchList(String qnaTitle); //검색
	List<QnaDto> selectListByPage(int page, int size); //페이징

}
