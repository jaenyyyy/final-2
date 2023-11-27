package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class HashtagListDto {
	private int hashNo;
    private String hashComment;
    private int resNo;
    private String hashComments;
}
