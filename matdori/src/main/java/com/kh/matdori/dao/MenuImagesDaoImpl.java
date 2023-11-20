package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.MenuImagesDto;

@Repository
public class MenuImagesDaoImpl implements MenuImagesDao{
	
	@Autowired
	    private SqlSession sqlSession;

	    @Override
	    public void insert(MenuImagesDto menuImagesDto) {
	        sqlSession.insert("menuImages.insert", menuImagesDto);
	    }
	}
