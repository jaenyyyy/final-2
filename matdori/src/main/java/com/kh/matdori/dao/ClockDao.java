package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.HolidayDto;
import com.kh.matdori.dto.WorkdayDto;
import com.kh.matdori.vo.ClockByResVO;

public interface ClockDao {

	void insert(ClockDto clockDto);
	ClockDto selectOne(int clockNo);
//	List<ClockDto> clockList();
	List<ClockDto> clockList(int clockResNo);
//	List<ClockDto> getClockListByResNo(int clockResNo);
	ClockDto getOneClock(int clockNo);
//	List<ClockDto> getClockList(int resNo);
	void addWorkday(WorkdayDto workdayDto);
//	void addHoliday(HolidayDto holidayDto);
//	ClockByResVO selectOneAvailableTime(int resNo, String selectedDate);

}
