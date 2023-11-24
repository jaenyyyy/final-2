package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.PickDto;
import com.kh.matdori.dto.RestaurantDto;

public interface PickDao {

	//찜하기
	void insert(PickDto pickDto);
	//찜 취소
	void delete(PickDto pickDto);
	//찜이 되있는지 확인
	boolean check(PickDto pickDto);
	//찜 개수 조회
	int count(int resNo);
	//찜한 식당 리스트 조회
	List<RestaurantDto> pickList(String customerId);
	

}
