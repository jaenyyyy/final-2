package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BusinessDaoImpl implements BusinessDao {

	@Autowired
	private SqlSession sqlSession; 
	
	@Override
	public void insert(BusinessDto businessDto) {
		sqlSession.insert("business.busJoin", businessDto);
	}

	@Override
	public BusinessDto selectOne(String busId) {
	    return sqlSession.selectOne("business.busSelectOne", busId);
	}

	@Override
	public boolean delete(String busId) {
	    int result = sqlSession.delete("business.busDelete", busId);
	    return result > 0; // result가 0보다 크면 삭제가 성공한 것으로 간주
	}

	
	@Override
	public boolean update(String busId, BusinessDto businessDto) {
	    businessDto.setBusId(busId); // busId를 BusinessDto에 설정
	    int result = sqlSession.update("business.busUpdateInfo", businessDto);
	    if (result == 0) {throw new NoTargetException();
	    }
	    return result > 0;
	}

	@Override
	public boolean updatePw(String busId, BusinessDto businessDto) {
	    businessDto.setBusId(busId);
	    int result = sqlSession.update("business.busUpdatePw", businessDto);
	    if (result == 0) {throw new NoTargetException();
	    }
	    return result > 0;
	}

    @Override
    public BusinessDto getBusinessDetails(String userId) {
        return sqlSession.selectOne("admin.getDetails", userId);
    }

	@Override
	public List<RestaurantDto> getMyRestaurantList(String busId) {
		return sqlSession.selectList("business.busResList", busId);
	}

	@Override
	public BusinessDto findByRegNo(String busRegNo) {
		return sqlSession.selectOne("business.findByRegNo",busRegNo);
	}

	@Override
	public BusinessDto selectOneRegNo(String busRegNo) {
		return sqlSession.selectOne("business.busSelectOneRegNo",busRegNo);
	}




}
