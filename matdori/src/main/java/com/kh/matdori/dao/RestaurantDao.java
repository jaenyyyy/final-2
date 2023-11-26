package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.ResSearchListDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.vo.RestaurantDetailVO;

public interface RestaurantDao{

	void insert(RestaurantDto restaurantDto);

	void delete(int resNo);

	void edit(int resNo, RestaurantDto restaurantDto);

	RestaurantDto selectOne(int resNo);

	RestaurantDetailVO selectDetail(int resNo);
	
	int sequence();
	
	int sequenceAttach();

	
	 public AttachDto findImageByNo(int attachNo);

    // 특정 resNo에 속하는 모든 이미지 정보를 조회하는 메서드
    public List<Integer> findImageNoByRes(int resNo);
    
    // 특정 resNo에 해당하는 이미지 정보를 삭제하는 메서드
    void deleteByResNo(int resNo);

    // 특정 resNo와 attachNo를 연결하는 메서드
    void insertResImage(int resNo, int attachNo);

    // 특정 resNo와 attachNo의 연결을 끊는 메서드
    void deleteResImage(int attachNo);
    
    
    
    
    //복합검색 리스트
    List<ResSearchListDto> resSearchList(Map<String, String> searchParams);
}
