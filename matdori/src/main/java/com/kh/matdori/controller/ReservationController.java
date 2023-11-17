package com.kh.matdori.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.ClockDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.SeatDao;
import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.SeatDto;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private ClockDao clockDao;
	@Autowired
	private SeatDao seatDao;
	
	@GetMapping("/insert")
	public String insert(Model model) {
		// clockList, seatList를 데이터베이스에서 조회
	    List<ClockDto> clockList = clockDao.clockList();
	    List<SeatDto> seatList = seatDao.seatList();
	    // 모델에 clockList, seatList를 추가
	    model.addAttribute("clockList", clockList);
	    model.addAttribute("seatList", seatList);
		return "reservation/booking";
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute ReservationDto reservationDto,
	                     @RequestParam("selectedClock") int clockNo,
	                     @RequestParam("selectedSeat") int seatNo,
	                     Model model) {

	    // 선택한 시간,좌석 값으로 시간,좌석 정보 조회
	    ClockDto selectedClock = clockDao.selectOne(clockNo);
	    SeatDto selectedSeat = seatDao.selectOne(seatNo);
	    // 시간 정보를 ReservationDto에 설정
	    if (selectedClock != null || selectedSeat != null) {
	        reservationDto.setRezClockNo(selectedClock.getClockNo());
	        reservationDto.setRezSeatNo(selectedSeat.getSeatNo());
	    }

	    reservationDao.insert(reservationDto);
	    return "redirect:detail";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int rezNo, Model model) {
		ReservationDto rezDto = reservationDao.selectOne(rezNo);
		model.addAttribute("rezDto", rezDto);
		return "reservation/rezDetail";
	}
}
