package com.kh.matdori.dao;

import com.kh.matdori.dto.ReservationDto;

public interface ReservationDao {

	void insert(ReservationDto reservationDto);
	
	boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo);
}
