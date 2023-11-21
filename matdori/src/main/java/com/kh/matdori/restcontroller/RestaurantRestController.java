package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.AdminDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.vo.RestaurantJudgeVO;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantRestController {

	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private AdminDao adminDao;
	
	@PostMapping("/")
	public void insert(@RequestBody RestaurantJudgeVO vo) {
		//시퀀스 꺼냄
		int resNo = restaurantDao.sequence();
		int judgeNo = adminDao.sequence();
		
		
		vo.getRestaurantDto().setResNo(resNo);
		
		//등록함
		 restaurantDao.insert(vo.getRestaurantDto());

	    // 시퀀스 값을 받아옴
	    
	    //resNo+judgeNo 설정
	    vo.getRestaurantJudgeDto().setResNo(resNo);
	    vo.getRestaurantJudgeDto().setResJudgeNo(judgeNo);
	    
	    //여기에 왜 안넣어지냐
	    adminDao.insertResJudge(vo.getRestaurantJudgeDto());
		}

	@DeleteMapping("/{resNo}")
	public void delete(@PathVariable int resNo) {
		restaurantDao.delete(resNo);
	}

	@PutMapping("/{resNo}")
	public void update(@RequestBody RestaurantDto restaurantDto, @PathVariable int resNo) {
		restaurantDto.setResNo(resNo); // resNo 설정 추가
		restaurantDao.edit(resNo, restaurantDto);
	}

	@GetMapping("/resNo/{resNo}")
	public RestaurantDto find(@PathVariable int resNo) {
		return restaurantDao.selectOne(resNo);
	}

}
