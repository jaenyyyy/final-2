package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ResSearchListDto {
	private int resNo;
    private String resName;
    private String regionName;
    private String hashComment;
}
