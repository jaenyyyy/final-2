package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ResSeat2Dto;
import com.kh.matdori.dto.Seat2Dto;
import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.dto.SeatListDto;

public interface Seat2Dao {

	int sequence();
	
	List<Seat2Dto> seat2List(int seatResNo); 
	
	void insertSeat2(Seat2Dto seat2Dto);

	void editSeat2(int seat2No, Seat2Dto seat2Dto);

	void deleteSeat2(int seatNo);

	
	Seat2Dto selectOne2(int seat2No);
	
	

}
