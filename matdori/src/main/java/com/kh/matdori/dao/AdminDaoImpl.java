package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.BusinessBlockDto;
import com.kh.matdori.dto.RestaurantAdminListDto;
import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.ResAdminVO;
import com.kh.matdori.vo.RestaurantJudgeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	
		//레스토랑 차단 기능
		@Override
		public void insertResBlock(RestaurantBlockDto restaurantBlockDto) {  //차단
			sqlSession.insert("admin.blockInsert", restaurantBlockDto);
		}

		@Override
		public boolean deleteResBlock(int resNo) {  //차단해제
			return sqlSession.delete("admin.blockDelete", resNo) > 0;
		}
		
		
		@Override
		public RestaurantBlockDto selectBlockOne(int resNo) {
			RestaurantBlockDto restaurantBlockDto 
				= sqlSession.selectOne("admin.resBlockDetail",resNo);
			return restaurantBlockDto;
		}

		
		
		//레스토랑 심사 기능
		@Override
		public int sequence() {
			return sqlSession.selectOne("admin.sequence");
		}

//		@Override
//		public void insertResJudge(RestaurantJudgeDto restaurantJudgeDto) {
//			sqlSession.insert("admin.resJudgeInsert", restaurantJudgeDto);
//			
//		}  vo 처리로 일단 주석

		@Override
		public boolean updateResJudge(RestaurantJudgeDto restaurantJudgeDto) {
			 Map<String, Object> params = new HashMap<>();
			    params.put("resJudgeNo", restaurantJudgeDto.getResJudgeNo());
			    params.put("resJudgeStatus", restaurantJudgeDto.getResJudgeStatus());
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
		
		
		@Override
		public void insertResJudge(RestaurantJudgeVO vo) {
			sqlSession.insert("admin.resJudgeInsert", vo);
		}

		//사업자 관리 리스트
	    @Override
	    public List<BusinessBlockDto> getAllBlockedBusinesses() {
	        return sqlSession.selectList("admin.getAllBlockedBusinesses");
	    }

	    //사업자 차단 상태 업데이트
	    @Override
	    public void updateBusBlock(BusinessBlockDto blockDto) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("busBlockComment", blockDto.getBusBlockComment());
	        params.put("busBlockStatus", blockDto.getBusBlockStatus());
	        params.put("busId", blockDto.getBusId());
	        
	        // 만약 해당 ID에 대한 레코드가 없다면, 삽입 작업을 수행할 수 있도록 DAO 메서드를 수정
	        int count = sqlSession.selectOne("admin.checkIfBusBlockExists", blockDto.getBusId());
	        if (count == 0) {
	            sqlSession.insert("admin.insertBusBlock", params);
	        } else {
	            sqlSession.update("admin.updateBusBlock", params);
	        }
	    }	
}
