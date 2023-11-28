package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.WorkdayDto;

public interface WorkdayDao {
	
	int sequence();
	
	void insert(WorkdayDto workdayDto);
	void deleteWorkday(int workdayResNo);
	List<WorkdayDto> selectList(int workdayResNo);
	WorkdayDto selectOne(int workdayNo);
	void updateWorkday(WorkdayDto workdayDto);
//    
//	List<WorkdayDto> workdayList();
//	void delete(int workdayNo);
//	
//	
//    void insert(ResWorkdayDto resWorkdayDto);
////    WorkdayDto selectOne(int workdayNo);
//    List<WorkdayDto> selectAllByResNo(int resNo);
//    void edit(WorkdayDto workdayDto);
//	void insertWorkday(WorkdayDto workdayDto);
//
//

}
