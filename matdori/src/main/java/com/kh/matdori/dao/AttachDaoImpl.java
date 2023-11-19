package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.AttachDto;

@Repository
public class AttachDaoImpl implements AttachDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("attach.sequence");
	}

	@Override
	public void insert(AttachDto attachDto) {
		sqlSession.insert("attach.insert", attachDto);
		
	}

	@Override
	public boolean delete(int attachNo) {
		return sqlSession.delete("attach.remove", attachNo) >0;
	}

	@Override
	public AttachDto selectOne(int attachNo) {
		return sqlSession.selectOne("attach.detail", attachNo);
	}

	@Override 
	public List<AttachDto> selectList() {
		return sqlSession.selectList("attach.list");
	}
	
	
	
}
