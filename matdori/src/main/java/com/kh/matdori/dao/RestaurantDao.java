package com.kh.matdori.dao;

import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.vo.RestaurantJudgeVO;

public interface RestaurantDao {

	void insert(RestaurantDto restaurantDto);

	void delete(int resNo);

	void edit(int resNo, RestaurantDto restaurantDto);

	RestaurantDto selectOne(int resNo);

	int sequence();
	
	
	
}
