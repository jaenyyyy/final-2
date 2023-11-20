package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.SeatDto;

public interface SeatDao {

	void insert(SeatDto seatDto);

	void edit(int seatNo, SeatDto seatDto);

	void delete(int seatNo);

	List<SeatDto> seatList();
//	List<SeatDto> seatList(int seatResNo);
	
	SeatDto selectOne(int seatNo);
	
	

}
