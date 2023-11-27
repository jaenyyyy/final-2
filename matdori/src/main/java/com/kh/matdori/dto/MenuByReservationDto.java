package com.kh.matdori.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuByReservationDto {
	private int rezNo;
	private int menuNo;
	private int menuQty;
	
	public static MenuByReservationDto mbrDto(int rezNo, int menuNo, int menuQty) {
		MenuByReservationDto dto = new MenuByReservationDto();
		dto.setRezNo(rezNo);
		dto.setMenuNo(menuNo);
		dto.setMenuQty(menuQty);
		return dto;
	}
	
	
	
	
//	private List<Integer> menuNos;  // 여러 메뉴번호를 저장하는 리스트
//    private List<Integer> menuQtys; // 각 메뉴에 대한 수량을 저장하는 리스트
//    
//    public List<Integer> getMenuNos() {
//        if (menuNos == null) {
//            menuNos = new ArrayList<>();
//        }
//        return menuNos;
//    }
//    
//    public List<Integer> getMenuQtys() {
//        if (menuQtys == null) {
//        	menuQtys = new ArrayList<>();
//        }
//        return menuQtys;
//    }
    
    
}
