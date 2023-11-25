package com.kh.matdori.vo;

import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.HolidayDto;
import com.kh.matdori.dto.WorkdayDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClockByResVO {
	
	private ClockDto clockDto;
	private WorkdayDto workdayDto;
	private HolidayDto holidayDto;
}
