package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.RegionDao;
import com.kh.matdori.dto.RegionDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/region")
public class RegionRestController {

	@Autowired
	private RegionDao regionDao;


	@PostMapping("/add")
	public void insert(@RequestBody RegionDto regionDto) {
		regionDao.insert(regionDto);
	}	
	}