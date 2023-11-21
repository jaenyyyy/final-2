package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.RestaurantJudgeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@Autowired
	private SqlSession sqlSession;

	
	
	//등록 다시
	@Override
	public void insert(RestaurantDto restaurantDto) {
		sqlSession.insert("restaurant.save", restaurantDto);
	}
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("restaurant.sequence");
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
		restaurantDto.setResNo(resNo);
		int result = sqlSession.update("restaurant.editResInfo", restaurantDto);
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

