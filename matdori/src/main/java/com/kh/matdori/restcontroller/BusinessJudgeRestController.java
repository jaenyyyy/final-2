package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.BusinessJudgeDao;
import com.kh.matdori.dto.BusinessJudgeDto;

@CrossOrigin
@RestController
@RequestMapping("/business/judge")
public class BusinessJudgeRestController {
	
	@Autowired
	private BusinessJudgeDao businessJudgeDao;
	
	/*
	 * //사업자 가입시 생성되는 사업자등록심사
	 * 
	 * @PostMapping("/reg") public void createBusinessJudge(@RequestBody
	 * BusinessJudgeDto businessJudgeDto) {
	 * businessJudgeDao.createBusinessJudge(businessJudgeDto); }
	 */

}
