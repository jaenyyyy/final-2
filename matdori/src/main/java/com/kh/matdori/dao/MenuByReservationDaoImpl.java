package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.MenuByReservationDto;

@Repository
public class MenuByReservationDaoImpl implements MenuByReservationDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(MenuByReservationDto menuByReservationDto) {
		sqlSession.insert("menuByReservation.insert", menuByReservationDto);
	}

}
