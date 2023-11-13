package com.kh.matdori.dao;

import com.kh.matdori.dto.RestaurantJudgeDto;

public interface AdminDao {
	
	//레스토랑 차단
	void insertResBlock(int resNo);  //차단
	boolean deleteResBlock(int resNo);  //차단해제

	
	//레스토랑 심사
	int sequence();  //시퀀스 등록
	void insertResJudge(RestaurantJudgeDto restaurantJudgeDto); //심사 등록
	boolean updateResJudge(RestaurantJudgeDto restaurantJudgeDto); //심사 수정
	boolean deleteResJudge(int resJudgeNo); //심사 삭제
	RestaurantJudgeDto selectOne(int resNo); //심사 상세 매장기준
	
	//목록은 복합검색으로 구현 예정
}
