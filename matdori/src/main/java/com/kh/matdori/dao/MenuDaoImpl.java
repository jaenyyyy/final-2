package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.MenuWithImagesVO;

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
		int result = sqlSession.delete("menu.deleteByMenuNo", menuNo);
		if (result == 0)
			throw new NoTargetException();
	}

	@Override
	public boolean edit(int menuNo, MenuDto menuDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("menuNo", menuNo);
		params.put("menuDto", menuDto);
		return sqlSession.update("menu.edit", params) > 0;
	}

	@Override
	public List<MenuWithImagesVO> selectList(int menuTypeNo) {
		return sqlSession.selectList("menu.selectMenuWithImages", menuTypeNo);
	}

	@Override
	public List<MenuWithImagesVO> getMenuByRes(int resNo) {
		return sqlSession.selectList("menu.selectMenuByRes", resNo);
	}

	@Override
	public int sequence() {
		return sqlSession.selectOne("menu.sequence");

	}

	@Override
	public void insertMenuImage(int menuNo, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("menuNo", menuNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("menu.insertMenuImage", params);
	}

	@Override
	public MenuWithImagesVO selectOne(int menuNo) {
		return sqlSession.selectOne("menu.menuByMenuNo", menuNo);
	}
}
