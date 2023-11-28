package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ResSeat2Dto;
import com.kh.matdori.dto.Seat2Dto;
import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.dto.SeatListDto;

public interface Seat2Dao {

	int sequence2();
	
	List<Seat2Dto> seat2List(); 
	
	void insert2(Seat2Dto seat2Dto);

	void edit2(int seat2No, Seat2Dto seat2Dto);

	void delete2(int seatNo);

	void insert2(ResSeat2Dto resSeat2Dto);
	List<SeatListDto> resSeatList(int resNo);
	
	Seat2Dto selectOne2(int seat2No);
	
	

}
