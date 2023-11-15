package com.kh.matdori.dao;

import com.kh.matdori.dto.MenuTypeDto;

public interface MenuTypeDao {

	void insert(MenuTypeDto menuTypeDto);

	void delete(int menuTypeNo);

}
