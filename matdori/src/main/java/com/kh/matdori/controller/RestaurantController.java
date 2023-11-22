package com.kh.matdori.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.vo.MenuWithImagesVO;
import com.kh.matdori.vo.RestaurantDetailVO;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ReviewDao reviewDao;

	
	@RequestMapping("/detail")
	public String detail(Model model, 
						 @RequestParam int resNo) {
		RestaurantDetailVO resDetail = restaurantDao.selectDetail(resNo);
//		List<MenuWithImagesVO> menuList = menuDao.getMenuByRes(resNo);
//		List<ReviewDto> reviewList = reviewDao.selectListByRes(resNo);
		
//		model.addAttribute("resDetail", resDetail);
//		model.addAttribute("menuList", menuList);
//		model.addAttribute("reviewList", reviewList); 
		//오류나서 주석처리
		
		return "restaurant/detail";
		
		
	}
}
