package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ClockDto;

public interface ClockDao {

	void insert(ClockDto clockDto);
	ClockDto selectOne(int clockNo);
	List<ClockDto> clockList();
//	List<ClockDto> clockList(int clockResNo);
	List<ClockDto> getClockListByResNo(int clockResNo);

}
