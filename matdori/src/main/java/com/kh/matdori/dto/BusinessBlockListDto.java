package com.kh.matdori.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BusinessBlockListDto {
    
    private String busId; // 사업자 아이디
    private String busName; // 사업자 명
    private String busEmail; // 사업자 이메일
    private String busBlockStatus; // 사업자 차단 상태(기본은 N, 차단은 Y)

    // 생성자, 게터/세터 등 추가할 수 있어요.
}
