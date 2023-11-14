package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.RestaurantAdminListDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.vo.ResAdminVO;

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
	
	//레스토랑 목록 (복합구문 추가)
	List<RestaurantAdminListDto> resAdminList(ResAdminVO vo);
}
