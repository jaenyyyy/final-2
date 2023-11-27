package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.MenuByReservationDto;
import com.kh.matdori.vo.MenuInfoVO;

public interface MenuByReservationDao {
	
	void insert(MenuByReservationDto menuByReservationDto);

	MenuByReservationDto selectOne(int menuNo);

	List<MenuByReservationDto> selectList(int rezNo);
	
	List<MenuInfoVO> menuInfo(int resNo);
}
