package com.kh.matdori.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuWithImagesVO {
	  private int menuNo;
	  private String menuName;
	  private BigDecimal menuPrice;
	  private String menuContent;
	  private int attachNo;
}
