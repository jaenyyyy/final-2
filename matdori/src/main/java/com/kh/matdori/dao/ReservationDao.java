package com.kh.matdori.dao;

import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;

public interface ReservationDao {
	int sequence();

	void insert(ReservationDto reservationDto);
	
	ReservationListDto selectOne(int rezNo);
	
	boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo);
}
