package com.kh.matdori.dao;

import com.kh.matdori.dto.WorkdayDto;
import java.util.List;

public interface WorkdayDao {
    
    void add(WorkdayDto workdayDto);
    WorkdayDto selectOne(int workdayNo);
    List<WorkdayDto> selectAllByResNo(int resNo);
    void edit(WorkdayDto workdayDto);
    void delete(int workdayNo);


}
