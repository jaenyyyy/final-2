package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.ReservationDto;

@Repository
public class ReservationDaoImpl implements ReservationDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(ReservationDto reservationDto) {
		sqlSession.insert("reservation.booking", reservationDto);
	}

}
