package com.kh.matdori.dto;

import java.util.List;

import com.kh.matdori.vo.WorkdayVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkdayDto {
	private int workdayNo;
	private int workdayResNo;
	private String workdayName;
	private String notWorkday;
	public List<WorkdayVO> findByWorkdayResNoAndNotWorkday(int resNo, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
