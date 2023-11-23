package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.MenuByResDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.vo.MenuWithImagesVO;

public interface MenuDao {

	void save(MenuDto menuDto);

	boolean delete(int menuNo);

	boolean edit(int menuNo, MenuDto menuDto);
	
//	List<MenuWithImagesVO> selectList(int menuTypeNo);
	
	List<MenuDto> selectListByMenuTypeNo(int menuTypeNo);

	List<MenuWithImagesVO> getMenuByRes(int resNo);

	int sequence();

	void insertMenuImage(int menuNo, int attachNo);

	MenuWithImagesVO selectOne(int menuNo);

	AttachDto findMenuImage(int menuNo);

	MenuDto selectOneByMenuNo(int menuNo);

}
