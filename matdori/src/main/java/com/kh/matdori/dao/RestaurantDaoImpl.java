package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.ResImagesDto;
import com.kh.matdori.dto.ResSearchListDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.RestaurantDetailVO;
import com.kh.matdori.vo.resSearchListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@Autowired
	private SqlSession sqlSession;

	// 등록 다시
	@Override
	public void insert(RestaurantDto restaurantDto) {
		sqlSession.insert("restaurant.save", restaurantDto);
	}

	@Override
	public int sequence() {
		return sqlSession.selectOne("restaurant.sequence");
	}

	// 삭제
	@Override
	public void delete(int resNo) {
		int result = sqlSession.delete("restaurant.deleteByResNo", resNo);
		if (result == 0)
			throw new NoTargetException();
	}

	// 기본정보수정
	@Override
	public void edit(int resNo, RestaurantDto restaurantDto) {
		restaurantDto.setResNo(resNo);
		int result = sqlSession.update("restaurant.editResInfo", restaurantDto);
		if (result == 0)
			throw new NoTargetException();
	}

	// 상세조회
	@Override
	public RestaurantDto selectOne(int resNo) {
		RestaurantDto restaurantDto = sqlSession.selectOne("restaurant.find", resNo);
		if (restaurantDto == null)
			throw new NoTargetException();
		return restaurantDto;
	}

	@Override
	public RestaurantDetailVO selectDetail(int resNo) {
		return sqlSession.selectOne("restaurant.resDetail", resNo);
	}


    @Override
    public int sequenceAttach() {
        return sqlSession.selectOne("restaurant.sequenceAttach");
    }

	@Override
	public List<Integer> findImageNoByRes(int resNo) {
		return sqlSession.selectList("restaurant.findImageNoByRes", resNo);
	}

	@Override
	public void deleteByResNo(int resNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertResImage(int resNo, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("resNo", resNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("restaurant.insertResImage",params);
	}

	@Override
	public void deleteResImage(int attachNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttachDto findImageByNo(int attachNo) {
		return sqlSession.selectOne("restaurant.findImageByNo",attachNo);
	}

	
	//복합검색 리스트
	@Override
	public List<ResSearchListDto> resSearchList(resSearchListVO vo) {
	    return sqlSession.selectList("restaurant.searchResList", vo);
	}

    // 공지 조회
    @Override
    public String findNotice(int resNo) {
        return sqlSession.selectOne("restaurant.findNotice", resNo);
    }

    // 공지 등록/수정
    @Override
    public void updateNotice(int resNo, String resNotice) {
    	sqlSession.update("updateNotice", new RestaurantDto(resNo, resNotice));
    }
	
}
