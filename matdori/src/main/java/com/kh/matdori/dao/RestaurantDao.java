package com.kh.matdori.dao;

import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.vo.RestaurantDetailVO;

public interface RestaurantDao {

	void insert(RestaurantDto restaurantDto);

	void delete(int resNo);

	void edit(int resNo, RestaurantDto restaurantDto);

	RestaurantDto selectOne(int resNo);

	RestaurantDetailVO selectDetail(int resNo);
	
	int sequence();
	
	
	
}
