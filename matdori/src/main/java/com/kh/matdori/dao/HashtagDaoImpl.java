package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.HashtagDto;
import com.kh.matdori.dto.HashtagListDto;
import com.kh.matdori.dto.ResHashtagDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class HashtagDaoImpl implements HashtagDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HashtagDto> hashList() {
	    return sqlSession.selectList("hashtag.hashList");
	}

	@Override
	public void insert(ResHashtagDto resHashtagDto) {
		sqlSession.insert("hashtag.resHashInsert", resHashtagDto);
	}

	@Override
	public void delete(int hashNo) {
		int result = sqlSession.delete("hashtag.resHashDelete", hashNo);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public List<HashtagListDto> resHashList(int resNo) {
		return sqlSession.selectList("hashtag.resHashList", resNo);
	}
	
	@Override
	public List<ResHashtagDto> resHashtagCount(int resNo) {
		return sqlSession.selectList("hashtag.resHashCount", resNo);
	}
}
