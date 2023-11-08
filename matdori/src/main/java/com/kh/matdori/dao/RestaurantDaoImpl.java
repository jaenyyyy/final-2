package com.kh.matdori.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@Autowired
	private SqlSession sqlSession;

	//등록
	@Override
	public void insert(RestaurantDto restaurantDto) {
	sqlSession.insert("restaurant.save",restaurantDto);
	}

	//삭제
	@Override
	public void delete(int resNo) {
		int result = sqlSession.delete("restaurant.deleteByResNo",resNo);
		if(result == 0) throw new NoTargetException();
	}

	//기본정보수정
	@Override
	public void edit(int resNo, RestaurantDto restaurantDto) {
		Map<String, Object> param = Map.of("resNo",resNo,"restaurantDto",restaurantDto);
		int result = sqlSession.update("restaurant.editResInfo", param);
		if(result ==0) 	throw new NoTargetException();		
	}
	//상세조회
	@Override
	public RestaurantDto selectOne(int resNo) {
		RestaurantDto restaurantDto = sqlSession.selectOne("restaurant.find", resNo);
		if(restaurantDto == null)
			throw new NoTargetException();
		return restaurantDto;
	}
	
	
}
