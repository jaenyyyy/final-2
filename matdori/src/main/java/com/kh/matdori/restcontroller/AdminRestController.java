package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.AdminDao;
import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantJudgeDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {
	
	@Autowired
	private AdminDao adminDao;
	
	
	//레스토랑 차단 기능
    @RequestMapping("/restaurant/block")
    public String resBlock(@RequestBody RestaurantBlockDto restaurantBlockDto) {
    	adminDao.insertResBlock(restaurantBlockDto);
    	return "redirect:/admin/restaurant/detail";
    }
    
    
    //레스토랑 차단 해제
    @RequestMapping("/restaurant/cancel")
    public String resCancel(@RequestBody RestaurantBlockDto restaurantBlockDto) {
    	adminDao.deleteResBlock(restaurantBlockDto.getResNo());
    	return "redirect:/admin/restaurant/detail";
    }
    
    
    //레스토랑 심사 수정 (승인 / 거절로 업데이트)
    @RequestMapping("/restaurant/judge")
    public void updateJudge(@RequestBody RestaurantJudgeDto restaurantJudgeDto) {
    	adminDao.updateResJudge(restaurantJudgeDto);
    }
    
    
    
}
