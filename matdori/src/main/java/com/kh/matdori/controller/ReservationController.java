package com.kh.matdori.controller;

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
import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.RestaurantDto;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationDao reservationDao;
	
	@GetMapping("/insert")
	public String insert() {
		return "reservation/booking";
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute ReservationDto reservationDto) {
		reservationDao.insert(reservationDto);
		return "redirect:rezDetail";
	}
}
