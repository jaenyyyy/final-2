package com.kh.matdori.vo;

import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.RestaurantJudgeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantJudgeVO {
	private RestaurantDto restaurantDto;
    private RestaurantJudgeDto restaurantJudgeDto;
    
	}