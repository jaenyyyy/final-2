package com.kh.matdori.dao;

import com.kh.matdori.dto.SeatDto;

public interface SeatDao {

	void insert(SeatDto seatDto);

	void edit(int seatNo, SeatDto seatDto);

	void delete(int seatNo);

}
