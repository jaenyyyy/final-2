package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ResSeatDto;
import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.dto.SeatListDto;

public interface SeatDao {

	List<SeatDto> seatList(); 
	
	void insert(SeatDto seatDto);

	void edit(int seatNo, SeatDto seatDto);

	void delete(int seatNo);

	void insert(ResSeatDto resSeatDto);
	List<SeatListDto> resSeatList(int resNo);
	
	SeatDto selectOne(int seatNo);

	List<SeatDto> seatList(int rezResNo);
	
	

}
