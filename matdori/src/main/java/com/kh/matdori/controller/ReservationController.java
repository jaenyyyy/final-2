package com.kh.matdori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dto.ReservationDto;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationDao reservationDao;
	
	@GetMapping("/booking")
	public String booking() {
		return "reservation/booking";
	}
	@PostMapping("/booking")
	public String booking(@ModelAttribute ReservationDto reservationDto) {
		reservationDao.insert(reservationDto);
		return "redirect:/";
	}
}
