package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.MenuTypeDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class MenuTypeDaoImpl implements MenuTypeDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(MenuTypeDto menuTypeDto) {
		sqlSession.insert("menuType.save", menuTypeDto);
	}

	@Override
	public void delete(int menuTypeNo) {
		int result = sqlSession.delete("menuType.deleteByMenuType",menuTypeNo);
		if(result==0) throw new NoTargetException();
	}

	@Override
	public boolean edit(int menuTypeNo, MenuTypeDto menuTypeDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("menuTypeNo",menuTypeNo);
		params.put("menuTypeDto",menuTypeDto);
		return sqlSession.update("menuType.edit", params) > 0;
	}

	@Override
	public List<MenuTypeDto> selectListByResNo(String resNo) {
	    Map<String, String> paramMap = new HashMap<>();
	    paramMap.put("resNo", resNo);
	    return sqlSession.selectList("menuType.selectListByResNo", paramMap);
	}

//	@Override
//	public List<MenuTypeDto> selectList(Integer menuTypeNo) {
//		return sqlSession.selectList("menuType.menuTypeList", menuTypeNo);
//}
	
}
