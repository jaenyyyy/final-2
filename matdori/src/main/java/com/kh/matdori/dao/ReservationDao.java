package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;

public interface ReservationDao {

	void insert(ReservationDto reservationDto);
	
	ReservationListDto selectOne(int rezNo);
	
	boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo);
	
	List<ReservationDto> rezList(String rezCustomerId);
}
