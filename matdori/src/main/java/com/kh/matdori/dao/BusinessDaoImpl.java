package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BusinessDaoImpl implements BusinessDao {

	@Autowired
	private SqlSession sqlSession; 
	
	@Override
	public void insert(BusinessDto businessDto) {
		sqlSession.insert("business.busJoin", businessDto);
	}

	@Override
	public void delete(String busId) {
		int result = sqlSession.delete("business.busDelete", busId);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public boolean update(String busId, BusinessDto businessDto) {
		// TODO Auto-generated method stub
		return false; 
	}

//	@Override
//	public boolean update(BusinessDto businessDto) {
//		int result = sqlSession.update("business.busUpdateInfo", businessDto);
//		if(result == 0) throw new NoTargetException();
//		return result > 0 ;
//	}
}
