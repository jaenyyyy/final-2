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

//	@Override
//	public int sequenceWorkday() {
//		return sqlSession.selectOne("clock2.sequenceWorkday");
//	}
//
//	
//	@Override
//	public List<Clock2Dto> clock2List() {
//	    List<Clock2Dto> list = sqlSession.selectList("clock2.clock2List");
//	    for (Clock2Dto item : list) {
//	        System.out.println(item); // 로그에 각 항목의 내용을 출력합니다.
//	    }
//	    return list;
//	}
//
//	@Override
//	public void insertClock2(ResClock2Dto resClock2Dto) {
//		sqlSession.insert("clock2.resClock2Insert", resClock2Dto);
//	}
//
//	@Override
//	public void deleteClock2(int clock2No) {
//		int result = sqlSession.delete("clock2.resClock2Delete", clock2No);
//		if(result == 0) throw new NoTargetException();
//	}
//	
//
//	@Override
//	public List<TimeListDto> resClock2List(int resNo) {
//		return sqlSession.selectList("clock2.resClock2List", resNo);
//	}
//
//	@Override
//	public List<WorkdayDto> workdayList() {
//		return sqlSession.selectList("clock2.workdayList");
//	}
//
//    // 레스토랑별 영업일 추가
//    @Override
//    public void insertWorkday(ResWorkdayDto resWorkdayDto) {
//        sqlSession.insert("workday.insertResWorkday", resWorkdayDto);
//    }
//
//    // 영업일 삭제
//    @Override
//    public void deleteWorkday(int workdayNo) {
//        sqlSession.delete("workday.deleteResWorkday", workdayNo);
//    }
//
//    // 레스토랑별 영업일 리스트 조회
//    @Override
//    public List<ResWorkdayDto> resWorkdayList(int workdayResNo) {
//        return sqlSession.selectList("workday.findResWorkday", workdayResNo);
//    }


}
