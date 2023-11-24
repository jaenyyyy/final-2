package com.kh.matdori.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dto.RestaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResImagesVO {
    private int resNo;
    private List<MultipartFile> resImages;

    // 여기에 필요한 추가 메타데이터 필드를 넣을 수 있습니다.

    // resNo를 기반으로 RestaurantDto를 생성하는 메서드
    // 이미지 파일이 아닌 다른 필요한 정보를 여기에 포함시킬 수 있습니다.
    public RestaurantDto getRestaurantDto() {
        return RestaurantDto.builder()
            .resNo(resNo)
            // 추가 필드
            .build();
    }
}



