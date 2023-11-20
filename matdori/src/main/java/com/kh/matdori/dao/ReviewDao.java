package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.ReviewDto;

public interface ReviewDao {
	
	int sequence(); //시퀀스 등록
	void insert(ReviewDto reviewDto); //리뷰 작성
	boolean update(ReviewDto reviewDto); //리뷰 수정
	boolean delete(int reviewNo); //리뷰 삭제
	
	List<ReviewDto> selectListByRes(int resNo); //리뷰 리스트 (매장)
	List<ReviewDto> selectListByCus(String reviewWriter); //리뷰 리스트 (이용자)
	ReviewDto selectOne(int reviewNo); //상세
	
	
	//리뷰 사진등록
	void connect(int reviewNo, int attacchNo);
	AttachDto findImage(int reviewNo);
	
}
