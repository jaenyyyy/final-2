package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.ClockDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.SeatDao;

@CrossOrigin
@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ClockDao clockDao;
	@Autowired
	private SeatDao seatDao;
	
	

}
