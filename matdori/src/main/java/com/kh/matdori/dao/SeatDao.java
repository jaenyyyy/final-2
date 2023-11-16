package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.vo.SeatListByResVO;

public interface SeatDao {

	void insert(SeatDto seatDto);

	void edit(int seatNo, SeatDto seatDto);

	void delete(int seatNo);

	List<SeatListByResVO> selectList();
	
	SeatDto selectOne(int seatNo);
	
	

}
