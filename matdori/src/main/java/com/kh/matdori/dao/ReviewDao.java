package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.vo.CusPaginationVO;

public interface ReviewDao {
	
	int sequence(); //시퀀스 등록
	void insert(ReviewDto reviewDto); //리뷰 작성
	boolean update(ReviewDto reviewDto); //리뷰 수정
	boolean delete(int reviewNo); //리뷰 삭제
	
	List<ReviewDto> selectListByRes(int resNo); //리뷰 리스트 (매장)
	List<ReviewDto> selectListByCus(String reviewWriter); //리뷰 리스트 (이용자)
	List<ReviewDto> selectListByCuspage(CusPaginationVO vo);//리뷰리스트 (이용자) 페이지네이션용
	int reviewCount(CusPaginationVO vo);//리뷰 페이지네이션용 카운트
	
	//리뷰 리스트 (이용자,페이지네이션)
	ReviewDto selectOne(int reviewNo); //상세
	ReviewDto selectOneInfo(int reviewNo); // 작성
	
	
	//리뷰 사진연결
	void connect(int reviewNo, int attacchNo);
	//리뷰 사진찾기
	AttachDto findImage(int reviewNo);

	//리뷰 평균 별점계산
	double getAverageRatingByRes(int resNo);

	int getCountOfReviewsByCustomerId(String customerId);
	
	
	

	
}
