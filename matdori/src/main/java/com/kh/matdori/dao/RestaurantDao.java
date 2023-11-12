package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantDto;

public interface RestaurantDao {

	void insert(RestaurantDto restaurantDto);

	void delete(int resNo);

	void edit(int resNo, RestaurantDto restaurantDto);

	RestaurantDto selectOne(int resNo);
	
	
	
	
	//레스토랑 차단
	void insertResBlock(int resNo);  //차단
	boolean deleteResBlock(int resNo);  //차단해제
	List<RestaurantBlockDto> selectResBlockList();  //차단 목록
	RestaurantBlockDto selectResBlockOne(int resNo); //차단 상세
}
