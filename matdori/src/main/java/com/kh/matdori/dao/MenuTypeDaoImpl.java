package com.kh.matdori.dao;

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
}
