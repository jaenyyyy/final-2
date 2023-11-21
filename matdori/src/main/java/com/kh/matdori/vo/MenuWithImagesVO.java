package com.kh.matdori.vo;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.matdori.dto.MenuDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuWithImagesVO {
	  private String menuName;
	  private Float menuPrice;
	  private String menuContent;
	  private MultipartFile menuImage;
	  
	  @JsonIgnore
	   public MenuDto getMenuDto() {
	      return MenuDto.builder()
	            .menuName(menuName)
	            .menuPrice(menuPrice)
	            .menuContent(menuContent)
	            .build();
	   }
	}

