package com.kh.matdori.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessJudgeListDto {
	private String busId;
	
    private int busJudgeNo;
    private BusinessDto business;  // BusinessDto를 포함

    private String busJudgeStatus;
    private Date busJudgeDate;

    private String busName;
    private String busRegNo;
    private Date busRegDate;

}
