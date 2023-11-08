package com.kh.matdori.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BusinessJudgeDto {
    private int busJudgeNo;
    private String busId;
    private String busJudgeStatus;
    private Date busJudgeDate;
    private String busJudgeComment;

}
