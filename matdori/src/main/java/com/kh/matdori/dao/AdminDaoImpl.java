package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.RestaurantAdminListDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.ResAdminVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	
		//레스토랑 차단 기능
		@Override
		public void insertResBlock(int resNo) {  //차단
			sqlSession.insert("admin.blockInsert", resNo);
		}

		@Override
		public boolean deleteResBlock(int resNo) {  //차단해제
			return sqlSession.delete("admin.blockDelete", resNo) > 0;
		}

		
		
		
		//레스토랑 심사 기능
		@Override
		public int sequence() {
			return sqlSession.selectOne("admin.sequence");
		}

		@Override
		public void insertResJudge(RestaurantJudgeDto restaurantJudgeDto) {
			sqlSession.insert("admin.resJudgeInsert", restaurantJudgeDto);
			
		}

		@Override
		public boolean updateResJudge(RestaurantJudgeDto restaurantJudgeDto) {
			 Map<String, Object> params = new HashMap<>();
			    params.put("resJudgeNo", restaurantJudgeDto.getResJudgeNo());
			    params.put("resJudgeStatus", restaurantJudgeDto.getResJudgeStatus());
			    params.put("resJudgeDate", restaurantJudgeDto.getResJudgeDate());
			    params.put("resJudgeComment", restaurantJudgeDto.getResJudgeComment());
			return sqlSession.update("admin.resJudgeUpdate", params) > 0;
		}

		@Override
		public boolean deleteResJudge(int resJudgeNo) {
			return sqlSession.delete("admin.resJudgeDelete", resJudgeNo) > 0;
		}

		@Override
		public RestaurantJudgeDto selectOne(int resNo) {
			RestaurantJudgeDto restaurantJudgeDto 
				= sqlSession.selectOne("admin.resJudgeDetail", resNo);
			if(restaurantJudgeDto == null) throw new NoTargetException();
			return restaurantJudgeDto;
		}



		//목록
		@Override
		public List<RestaurantAdminListDto> resAdminList(ResAdminVO vo){
			List<RestaurantAdminListDto> list = sqlSession.selectList("admin.resAdminList", vo);
			return list;
		}
		
		//상세
		@Override
		public RestaurantAdminListDto resAdminOne(int resNo) {
			RestaurantAdminListDto restaurantAdminListDto
				= sqlSession.selectOne("admin.resAdminDetail", resNo);
			if(restaurantAdminListDto == null) throw new NoTargetException();
			return restaurantAdminListDto;
		}
}
