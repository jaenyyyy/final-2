package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.MenuDto;

public interface MenuDao {

	void save(MenuDto menuDto);

	void delete(int menuNo);

	List<MenuDto> selectList();

	MenuDto selectOne(int menuNo);

	boolean edit(int menuNo, MenuDto menuDto);

}
