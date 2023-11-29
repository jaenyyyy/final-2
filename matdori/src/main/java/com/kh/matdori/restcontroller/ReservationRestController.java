package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	
	@PostMapping("/checkDate/{workdayNo}")
	public String checkDate(
							@PathVariable int workdayNo,
							@RequestParam String selectedDate,
							@RequestParam int rezResNo
							) {
		log.debug("workdayNo={}",workdayNo);
		WorkdayDto workdayDto = workdayDao.selectOne(workdayNo);
		WorkdayVO workdayVO = reservationDao.selectDate(selectedDate);
		
		if(workdayDto.getWorkdayName().equals(workdayVO.getDayName()) 
				&& rezResNo == workdayVO.getRezResNo()) {
			ReservationDto reservationDto = new ReservationDto();
//			reservationDto.setInputDate(selectedDate);
			return workdayDto.getNotWorkday();
		}
		else {
			return null;
		}
		
	
	}
	
	

}
