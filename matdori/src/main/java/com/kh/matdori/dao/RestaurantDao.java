package com.kh.matdori.dao;

import com.kh.matdori.dto.RestaurantDto;

public interface RestaurantDao {

	void insert(RestaurantDto restaurantDto);

	void delete(int resNo);

}
