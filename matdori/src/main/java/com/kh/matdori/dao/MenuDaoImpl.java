package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
