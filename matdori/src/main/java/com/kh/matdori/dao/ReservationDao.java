package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ReservationDto;

public interface ReservationDao {
	int sequence();

	void insert(ReservationDto reservationDto);
	
	ReservationDto selectOne(int rezNo);  //결제상세
	
	boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo);
	
	List<ReservationDto> rezList(String rezCustomerId); //회원별 예약조회

}
