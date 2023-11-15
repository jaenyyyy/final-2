package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.RestaurantDto;

public interface BusinessDao {

	void insert(BusinessDto businessDto);
	BusinessDto selectOne(String busId);

	boolean updatePw(String busId, BusinessDto businessDto);
	boolean update(String busId, BusinessDto businessDto);
	boolean delete(String busId);
	
	BusinessDto getBusinessDetails(String userId);
	List<RestaurantDto> getMyRestaurantList(String busId);
}
