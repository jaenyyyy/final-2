package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;
import com.kh.matdori.vo.CusPaginationVO;
import com.kh.matdori.vo.MenuWithImagesVO;

import com.kh.matdori.dto.ReviewDto;

public interface ReservationDao {
	int sequence();

	void insert(ReservationDto reservationDto);

	ReservationDto selectOne(int rezNo); // 결제상세

	boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo);

	List<ReservationListDto> rezList(CusPaginationVO vo); // 회원별 예약조회리스트

	int rezCount(CusPaginationVO vo);//페이지네이션?
	
	List<MenuWithImagesVO> menuList(int rezNo); // 다수의 메뉴

}
