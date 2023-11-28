package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;
import com.kh.matdori.vo.CusPaginationVO;
import com.kh.matdori.vo.MenuWithImagesVO;
import com.kh.matdori.vo.WorkdayVO;
import com.kh.matdori.dto.ReviewDto;


public interface ReservationDao {
   int sequence();

   void insert(ReservationDto reservationDto);
   
   ReservationDto selectOne(int rezNo);  //결제상세
   
   boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo);
   
   List<ReservationListDto> rezList(String rezCustomerId); //회원별 예약조회
   
   List<ReservationListDto> rezList(CusPaginationVO vo); //회원별 예약조회
   

   List<MenuWithImagesVO> menuList(int rezNo); //다수의 메뉴
   
   WorkdayVO selectDate(String inputDate);



}
