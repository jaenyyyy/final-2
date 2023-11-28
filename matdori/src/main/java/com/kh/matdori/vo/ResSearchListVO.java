package com.kh.matdori.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ResSearchListVO {
	private Integer resNo;
	private String resName;
    private String regionName;
    private String hashComment;
    private String keyword;
}
