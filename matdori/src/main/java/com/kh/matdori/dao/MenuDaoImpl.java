package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.MenuByResDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void save(MenuDto menuDto) {
		sqlSession.insert("menu.save", menuDto);
	}

	@Override
	public void delete(int menuNo) {
		int result = sqlSession.delete("menu.deleteByMenuNo",menuNo);
		if(result==0) 	throw new NoTargetException();
	}
//
//	@Override
//	public List<MenuByResDto> selectList() {
//		return sqlSession.selectList("menu.menuListByResNo");
//	}

	@Override
	public MenuDto selectOne(int menuNo) {
		return sqlSession.selectOne("menu.findByMenuNo", menuNo);
	}

	@Override
	public boolean edit(int menuNo, MenuDto menuDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("menuNo", menuNo);
		params.put("menuDto", menuDto);
		return sqlSession.update("menu.edit", params) > 0;
	}

	@Override
	public List<MenuByResDto> selectList(Integer resNo) {
			return sqlSession.selectList("menu.menuListByResNo",resNo);
		}
	}



