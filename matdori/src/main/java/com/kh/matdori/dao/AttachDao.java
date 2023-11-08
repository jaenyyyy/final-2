package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.AttachDto;

public interface AttachDao {
	void insert(AttachDto attachDto);  //등록
	boolean delete(int attachNo);  //삭제
	AttachDto selectOne(int attachNo);  //상세
	List<AttachDto> selectList();  //조회
	
}
