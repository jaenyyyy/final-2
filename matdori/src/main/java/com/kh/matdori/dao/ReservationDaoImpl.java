package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;

@Repository
public class ReservationDaoImpl implements ReservationDao{
	@Autowired
	private SqlSession sqlSession;
	//예약등록
	@Override
	public void insert(ReservationDto reservationDto) {
		sqlSession.insert("reservation.booking", reservationDto);
	}
	
	@Override
	public ReservationListDto selectOne(int rezNo) {
		return sqlSession.selectOne("reservation.detail", rezNo);
	}
	
	
	
	@Override
	public boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo) {
		//예약이 이미 돼있는지를 판단할 때 필요한 정보를 전달
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("rezResNo", rezResNo);
	    parameters.put("rezClockNo", rezClockNo);
	    parameters.put("rezSeatNo", rezSeatNo);
		
	    //예약이 안되어 있다면 null을 반환
	    return sqlSession.selectOne("reservation.isInReservation", parameters) != null;
	}
	
	
	
	@Override
	public List<ReservationDto> rezList(String rezCustomerId) {
		List<ReservationDto> rezList = sqlSession.selectList("rezList", rezCustomerId);
		return rezList;
	}

}
