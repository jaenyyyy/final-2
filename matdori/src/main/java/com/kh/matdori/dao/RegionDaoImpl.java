package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.RegionDto;

@Repository
public class RegionDaoImpl implements RegionDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(RegionDto regionDto) {
		sqlSession.insert("region.add",regionDto);
	}
}
