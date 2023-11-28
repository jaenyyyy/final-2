package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.Clock2Dao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.WorkdayDao;
import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.WorkdayDto;
import com.kh.matdori.vo.WorkdayVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/rest/reservation")
public class ReservationRestController {
	@Autowired
	private Clock2Dao clock2Dao;
	@Autowired
	private WorkdayDao workdayDao;
	@Autowired
	private ReservationDao reservationDao;
	
	@PostMapping("/checkDate")
	public String checkDate(
							@RequestParam int workdayNo,
							@RequestParam String selectedDate,
							@RequestParam int rezResNo
							) {
		WorkdayDto workdayDto = workdayDao.selectOne(workdayNo);
		WorkdayVO workdayVO = reservationDao.selectDate(selectedDate);
		
		if(workdayDto.getWorkdayName().equals(workdayVO.getWorkdayName()) 
				&& rezResNo == workdayVO.getRezResNo()) {
			ReservationDto reservationDto = new ReservationDto();
			reservationDto.setInputDate(selectedDate);
			return workdayDto.getNotWorkday();
		}
		else {
			return null;
		}
		
	
	}
	
	

}
