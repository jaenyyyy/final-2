package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuByResDto {
    private int resNo;
    private String resName;
    private String menuTypeName;
    private int menuNo;
    private String menuName;
    private Float menuPrice;
    private String menuContent;
    private int imageId;
}
