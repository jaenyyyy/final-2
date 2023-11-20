package com.kh.matdori.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.ClockDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.SeatDao;
import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;
import com.kh.matdori.dto.SeatDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ClockDao clockDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private MenuDao menuDao;
	
	@GetMapping("/insert")
	public String insert(Model model,
						 @RequestParam("rezResNo") int rezResNo
						) {
		// clockList, seatList를 데이터베이스에서 조회
	    List<ClockDto> clockList = clockDao.clockList(rezResNo);
	    List<SeatDto> seatList = seatDao.seatList(rezResNo);
//	    List<MenuDto> menuList = menuDao.menuList(rezResNo);
	    // 모델에 clockList, seatList를 추가
	    model.addAttribute("clockList", clockList);
	    model.addAttribute("seatList", seatList);
		return "reservation/booking";
	}
	@PostMapping("/insert")
	public String insert(HttpSession session,
						 @ModelAttribute ReservationDto reservationDto,
//						 @RequestParam("rezResNo") int rezResNo,
	                     @RequestParam("selectedClock") int clockNo,
	                     @RequestParam("selectedSeat") int seatNo) {
		int rezNo = reservationDao.sequence();
		log.debug("rezNo={}",rezNo);
		reservationDto.setRezNo(rezNo);
		
		//회원별 예약 처리
		String rezCustomerId = (String)session.getAttribute("name");
		reservationDto.setRezCustomerId(rezCustomerId);
		//매장별 처리
//		reservationDto.setRezResNo(rezResNo);
		
	    // 선택한 시간,좌석 값으로 시간,좌석 정보 조회
	    ClockDto selectedClock = clockDao.selectOne(clockNo);
	    SeatDto selectedSeat = seatDao.selectOne(seatNo);
	    // 시간 정보를 ReservationDto에 설정
	    if (selectedClock != null && selectedSeat != null) {
	        reservationDto.setRezClockNo(selectedClock.getClockNo());
	        reservationDto.setRezSeatNo(selectedSeat.getSeatNo());
	    }

	    reservationDao.insert(reservationDto);
//	    log.debug("dto={}",reservationDto);
	    return "redirect:detail?rezNo="+reservationDto.getRezNo();
	}
	
	@RequestMapping("/detail")
	public String detail(
//			HttpSession session,
//						 @ModelAttribute ReservationDto reservationDto,
						 @RequestParam int rezNo,
						 Model model) {
		ReservationListDto rezDto = reservationDao.selectOne(rezNo);
		model.addAttribute("rezDto", rezDto);
		return "reservation/rezDetail";
	}
}
