package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.HashtagDto;
import com.kh.matdori.dto.ResHashtagDto;

public interface HashtagDao {
	
	//관리자가 등록해놓고 보여주는거
	List<HashtagDto> hashList(int hashNo);
	
	
	//사업자 시점 
	void insert(ResHashtagDto resHashtagDto); //해시태그 설정
	void delete(int hashNo); // 해시태그 삭제 
	List<ResHashtagDto> resHashList(int resNo); //등록된 해시태그 리스트
}
