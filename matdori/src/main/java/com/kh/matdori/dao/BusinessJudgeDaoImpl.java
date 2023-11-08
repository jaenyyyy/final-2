package com.kh.matdori.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.BusinessJudgeDto;

//BusinessJudgeDaoImpl.java

@Repository
public class BusinessJudgeDaoImpl implements BusinessJudgeDao {

	@Autowired
	private SqlSession sqlSession;
	
    @Override
    public void createBusinessJudge(BusinessJudgeDto businessJudgeDto) {
        sqlSession.insert("businessJudge.createBusinessJudge", businessJudgeDto);
    }
}
