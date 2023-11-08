package com.kh.matdori.dao;

import com.kh.matdori.dto.MenuDto;

public interface MenuDao {

	void save(MenuDto menuDto);

	void delete(int menuNo);

}
