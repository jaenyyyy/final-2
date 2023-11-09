package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.ClockDao;
import com.kh.matdori.dto.ClockDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/clock")
public class ClockRestController {

	@Autowired
	private ClockDao clockDao;
	
	@PostMapping("/")
	public void insert(@RequestBody ClockDto clockDto) {
		clockDao.insert(clockDto);
	}
}
