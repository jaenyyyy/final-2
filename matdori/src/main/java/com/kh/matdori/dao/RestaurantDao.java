package com.kh.matdori.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dto.ResImagesDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.vo.RestaurantDetailVO;

public interface RestaurantDao {

	void insert(RestaurantDto restaurantDto);

	void delete(int resNo);

	void edit(int resNo, RestaurantDto restaurantDto);

	RestaurantDto selectOne(int resNo);

	RestaurantDetailVO selectDetail(int resNo);
	
	int sequence();
	int sequenceAttach();

	void insertResImage(int resNo, List<MultipartFile> resImages);

	List<ResImagesDto> selectResImagesByResNo(int resNo);

	boolean deleteResImage(int attachNo);

	
	
	
}
