package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.HolidayDto;
import com.kh.matdori.dto.WorkdayDto;
import com.kh.matdori.vo.ClockByResVO;

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
	public List<ClockDto> clockList(int clockResNo) {
		return sqlSession.selectList("clock.clockList", clockResNo);
	}
	
	
	////////// 작업중///////////
	@Override
	public ClockDto getOneClock(int clockNo) {
		return sqlSession.selectOne("clock.oneOfClock, clockNo");
	}

	@Override
	public void addWorkday(WorkdayDto workdayDto) {
		sqlSession.insert("addWorkday",workdayDto);
	}



//
//
//	 @Override
//	    public ClockByResVO selectOneAvailableTime(int resNo, String selectedDate) {
//	        Map<String, Object> params = new HashMap<>();
//	        params.put("resNo", resNo);
//	        params.put("selectedDate", selectedDate);
//	        
//	        return sqlSession.selectOne("clock.selectOneAvailableTime", params);
//	    }
//	
//	@Override
//	public List<ClockDto> getClockList(int resNo) {
//		return sqlSession.selectList("clock.selectByResNo", resNo);
//	}
}
