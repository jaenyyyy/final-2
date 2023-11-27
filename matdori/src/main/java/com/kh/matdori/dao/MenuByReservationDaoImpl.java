package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.MenuByReservationDto;
import com.kh.matdori.vo.MenuInfoVO;

@Repository
public class MenuByReservationDaoImpl implements MenuByReservationDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(MenuByReservationDto menuByReservationDto) {
		sqlSession.insert("menuByReservation.insert", menuByReservationDto);
	}
	
	@Override
	public MenuByReservationDto selectOne(int menuNo) {
		return sqlSession.selectOne("menuByReservation.selectOne", menuNo);
	}
	
	@Override
	public List<MenuByReservationDto> selectList(int rezNo) {
		return sqlSession.selectList("menuByReservation.rezMenus", rezNo);
	}
	
	@Override
	public List<MenuInfoVO> menuInfo(int rezNo) {
		return sqlSession.selectList("menuByReservation.menuInfo", rezNo);
	}	

}
