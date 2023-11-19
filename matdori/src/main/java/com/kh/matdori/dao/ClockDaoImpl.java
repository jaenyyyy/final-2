package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.ClockDto;

@Repository
public class ClockDaoImpl implements ClockDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(ClockDto clockDto) {
		sqlSession.insert("clock.add", clockDto);
	}
	
	@Override
	public ClockDto selectOne(int clockNo) {
		return sqlSession.selectOne("clock.oneOfClock", clockNo);
	}
	
	@Override
	public List<ClockDto> clockList() {
		return sqlSession.selectList("clock.clockList");
	}
	
	@Override
	public List<ClockDto> getClockListByResNo(int clockResNo) {
		return sqlSession.selectList("clock.getClockListByResNo", clockResNo);
	}
}
