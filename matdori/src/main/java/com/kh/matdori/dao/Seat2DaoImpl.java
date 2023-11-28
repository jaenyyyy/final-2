package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.ResSeat2Dto;
import com.kh.matdori.dto.Seat2Dto;
import com.kh.matdori.dto.SeatListDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class Seat2DaoImpl implements Seat2Dao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert2(Seat2Dto seat2Dto) {
		sqlSession.insert("seat2.insert2", seat2Dto);
	}

	@Override
	public void edit2(int seat2No, Seat2Dto seat2Dto) {
		Map<String, Object> param = Map.of("seat2No", seat2No, "seat2Dto", seat2Dto);
		int result = sqlSession.update("seat.edit2", param);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public void delete2(int seat2No) {
		int result = sqlSession.delete("seat2.remove2", seat2No);
		if(result == 0) throw new NoTargetException();
	}
	
	@Override
	public List<SeatListDto> resSeatList(int resNo) {
		return sqlSession.selectList("seat2.resSeat2List",resNo);
	}
	
	@Override
	public Seat2Dto selectOne2(int seat2No) {
		return sqlSession.selectOne("seat2.oneOfSeat2", seat2No);
	}

	@Override
	public void insert2(ResSeat2Dto resSeat2Dto) {
		sqlSession.insert("seat2.resSeat22Insert", resSeat2Dto);
	}

	@Override
	public List<Seat2Dto> seat2List() {
		return sqlSession.selectList("seat2.seat2List");
	}

	@Override
	public int sequence2() {
		return sqlSession.selectOne("seat2.sequence2");
	}
}
