package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.Clock2Dto;
import com.kh.matdori.dto.ResClock2Dto;
import com.kh.matdori.dto.ResWorkdayDto;
import com.kh.matdori.dto.TimeListDto;
import com.kh.matdori.dto.WorkdayDto;

public interface Clock2Dao {

	int sequence();
	
	

	void insertClock2(Clock2Dto clock2Dto);
	void deleteClock2(int clock2No);
	List<Clock2Dto> clock2List(int clock2ResNo);
	  Clock2Dto selectOneClock2(int clock2No);
	
	
	
	
//	void insertClock2(ResClock2Dto resClock2Dto);
//	void insertWorkday(ResWorkdayDto resWorkdayDto);
//	void deleteWorkday(int workdayResNo);
	//레스토랑별 시간 리스트
//	List<TimeListDto> resClock2List(int resNo);
//	List<TimeListDto> res	WorkdayList(int resNo);
//	List<ResWorkdayDto> resWorkdayList(int workdayResNo);
//	List<WorkdayDto> workdayList();
	
//	int sequenceWorkday();
	

}
