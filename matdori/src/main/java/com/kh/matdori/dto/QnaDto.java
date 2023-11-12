package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class QnaDto {
	private int qnaNo;
	private String qnaWriter;
	private String qnaCategory;
	private String qnaTitle;
	private String qnaAnswer;
}
