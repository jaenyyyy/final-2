package com.kh.matdori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private RestaurantDao restaurantDao;
	
	@GetMapping("/insert")
	public String insert() {
		return "reservation/booking";
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute ReservationDto reservationDto) {
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
