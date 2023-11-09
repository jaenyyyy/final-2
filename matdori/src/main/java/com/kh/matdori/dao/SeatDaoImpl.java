package com.kh.matdori.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class SeatDaoImpl implements SeatDao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(SeatDto seatDto) {
		sqlSession.insert("seat.insert", seatDto);
	}

	@Override
	public void edit(int seatNo, SeatDto seatDto) {
		Map<String, Object> param = Map.of("seatNo", seatNo, "seatDto", seatDto);
		int result = sqlSession.update("seat.edit", param);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public void delete(int seatNo) {
		int result = sqlSession.delete("book.remove", seatNo);
		if(result == 0) throw new NoTargetException();
	}

}
