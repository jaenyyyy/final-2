package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.MenuTypeDto;

public interface MenuTypeDao {

	void insert(MenuTypeDto menuTypeDto);

	void delete(int menuTypeNo);

	boolean edit(int menuTypeNo, MenuTypeDto menuTypeDto);

	List<MenuTypeDto> selectListByResNo(String resNo);

}
