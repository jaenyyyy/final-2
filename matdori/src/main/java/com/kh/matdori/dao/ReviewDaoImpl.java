package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("review.sequence");
	}

	@Override
	public void insert(ReviewDto reviewDto) {
		sqlSession.insert("review.write", reviewDto);
	}

	@Override
	public boolean update(ReviewDto reviewDto) {
		Map<String, Object> params = new HashMap <>();
		params.put("reviewNo", reviewDto.getReviewNo());
		params.put("reviewDto", reviewDto);
		return sqlSession.update("review.change", params) > 0;
	}

	@Override
	public boolean delete(int reviewNo) {
		return sqlSession.delete("review.delete", reviewNo) > 0;
	}

	@Override
	public List<ReviewDto> selectListByRes(int resNo) {
		List<ReviewDto> listByRes = sqlSession.selectList("review.listByRes", resNo);
		return listByRes;
	}

	// 리뷰이미지 + 리뷰리스트
	@Override
	public List<ReviewDto> selectListByCus(String reviewWriter) {
		List<ReviewDto> listByCus = sqlSession.selectList("review.listByCus", reviewWriter);
		return listByCus;
	}

	@Override
	public ReviewDto selectOne(int reviewNo) {
		ReviewDto reviewDto = sqlSession.selectOne("review.detail", reviewNo);
		if(reviewDto == null) throw new NoTargetException();
		return reviewDto;
	}

	@Override
	public void connect(int reviewNo, int attachNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reviewNo", reviewNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("review.connect", params);
	}

	@Override
	public AttachDto findImage(int reviewNo) {
		return sqlSession.selectOne("findImage", reviewNo);
	}

}
