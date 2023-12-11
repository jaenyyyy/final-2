package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.Clock2Dto;
import com.kh.matdori.dto.ResClock2Dto;
import com.kh.matdori.dto.ResWorkdayDto;
import com.kh.matdori.dto.TimeListDto;
import com.kh.matdori.dto.WorkdayDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class Clock2DaoImpl implements Clock2Dao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("clock2.sequence");
	}

	@Override
	public void insertClock2(Clock2Dto clock2Dto) {
		sqlSession.insert("clock2.insertClock2", clock2Dto);
	}

	@Override
	public void deleteClock2(int clock2No) {
		int result = sqlSession.delete("clock2.deleteClock2",clock2No);
	}

	@Override
	public List<Clock2Dto> clock2List(int clock2ResNo) {
		return sqlSession.selectList("clock2.clock2List", clock2ResNo);
	}

	@Override
	public Clock2Dto selectOneClock2(int clock2No) {
		return sqlSession.selectOne("clock2.clock2One",clock2No);
	}

}
