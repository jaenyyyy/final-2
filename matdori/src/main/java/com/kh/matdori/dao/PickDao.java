package com.kh.matdori.dao;

import com.kh.matdori.dto.PickDto;

public interface PickDao {

	//찜하기
	void insert(PickDto pickDto);
	//찜 취소
	void delete(PickDto pickDto);
	//찜이 되있는지 확인
	boolean check(PickDto pickDto);
	//찜 개수 조회(안쓸수도 있음)
	int count(int resNo);
	

}
