package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.BusinessDto;

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
}
