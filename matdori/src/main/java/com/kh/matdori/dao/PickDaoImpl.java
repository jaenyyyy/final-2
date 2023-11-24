package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.PickDto;
import com.kh.matdori.dto.RestaurantDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PickDaoImpl implements PickDao{
	
	@Autowired
	SqlSession sqlSession;

	//찜하기
	@Override
	public void insert(PickDto pickDto) {
		sqlSession.insert("customer.pickInsert", pickDto);
	}

	//찜 취소
	@Override
	public void delete(PickDto pickDto) {
		sqlSession.delete("customer.pickDelete",pickDto);
	}

	@Override
	public boolean check(PickDto pickDto) {
	    Integer result = sqlSession.selectOne("customer.checkPick", pickDto);
	    return result != null && result > 0; 
	    // 결과가 null이 아니고 0보다 큰 경우(찜이되있으면) true 반환
	}

	//찜횟수 
	@Override
	public int count(int resNo) {
	    return sqlSession.selectOne("customer.getPicksCount", resNo);
	}


	//찜한 목록 조회
	@Override
	public List<RestaurantDto> pickList(String customerId) {
	    return sqlSession.selectList("customer.getPickResByUser", customerId);
	}


}
