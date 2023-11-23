package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.BusinessBlockDto;
import com.kh.matdori.dto.BusinessJudgeListDto;
import com.kh.matdori.dto.RestaurantAdminListDto;
import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.vo.BusBlockPaginationVO;
import com.kh.matdori.vo.BusPaginationVO;
import com.kh.matdori.vo.ResAdminVO;
import com.kh.matdori.vo.RestaurantJudgeVO;

public interface AdminDao {
	
	//레스토랑 차단
	void insertResBlock(RestaurantBlockDto restaurantBlockDto); //차단
	boolean deleteResBlock(int resNo);  //차단해제
	RestaurantBlockDto selectBlockOne(int resNo); //차단 상세

	
	//레스토랑 심사
	int sequence();  //시퀀스 등록
	void insertResJudge(RestaurantJudgeDto restaurantJudgeDto); //심사 등록  (리액트 insert에서 넘기느라 vo로 생성했다)
	boolean updateResJudge(RestaurantJudgeDto restaurantJudgeDto); //심사 수정
	boolean deleteResJudge(int resJudgeNo); //심사 삭제
	RestaurantJudgeDto selectOne(int resNo); //심사 상세 매장기준
	
	//레스토랑 관리자 - 목록 (복합구문 추가)
	List<RestaurantAdminListDto> resAdminList(ResAdminVO vo);
	
	//레스토랑 관리자 - 상세
	RestaurantAdminListDto resAdminOne(int resNo);
	
	//사업자 차단 관리 리스트
	List<BusinessBlockDto> getBusBlocklist(BusBlockPaginationVO vo);
	
	//사업자 등록 심사 리스트 
	List<BusinessJudgeListDto> getList(BusPaginationVO vo);
	
	//사업자 차단 페이지네이션용
	int countBlockList(BusBlockPaginationVO vo);
	
	//사업자 심사 페이지네이션용
	int countList(BusPaginationVO vo); //검색 + 페이지네이션
	
//	// 사업자 차단 상태 업데이트 이전에 존재 여부 확인을 위한 메서드 추가
//    int checkIfBusBlockExists(String busId);
	//사업자 차단 상태 업데이트
	void updateBusBlock(BusinessBlockDto blockDto);
	
	
}
