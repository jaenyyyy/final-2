package com.kh.matdori;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.matdori.dto.Clock2Dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Clock2Test {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		List<Clock2Dto> list = sqlSession.selectList("clock2.clock2List");
		log.debug("조회 결과 = {}", list.size());
		for(Clock2Dto dto : list) {
			log.debug("dto = {}", dto);
		}
	}
}
