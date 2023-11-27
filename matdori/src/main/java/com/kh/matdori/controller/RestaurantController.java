package com.kh.matdori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.HashtagDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.HashtagListDto;
import com.kh.matdori.dto.ResSearchListDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.vo.MenuWithImagesVO;
import com.kh.matdori.vo.ResAdminVO;
import com.kh.matdori.vo.resSearchListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private HashtagDao hashtagDao;

	
	@RequestMapping("/detail")
	public String detail(Model model, 
						 @RequestParam int resNo) {
		RestaurantDto resDto = restaurantDao.selectOne(resNo);
		List<MenuWithImagesVO> menuListByRes = menuDao.getMenuByRes(resNo);
		List<ReviewDto> reviewByRes = reviewDao.selectListByRes(resNo);
		List<HashtagListDto> resHashDto = hashtagDao.resHashList(resNo);
		

//		model.addAttribute("resDetail", resDetail);
//		model.addAttribute("menuList", menuList);
//		model.addAttribute("reviewList", reviewList); 

		model.addAttribute("resDto", resDto);
		model.addAttribute("menuListByRes", menuListByRes);
		model.addAttribute("reviewByRes", reviewByRes);		
	    // 별점 평균 조회
        double averageRating = reviewDao.getAverageRatingByRes(resNo);
        model.addAttribute("averageRating", averageRating);
		model.addAttribute("resHashDto", resHashDto);
//		log.debug("list= {}",resDto);
//		log.debug("list= {}",menuListByRes);
//		log.debug("list= {}",reviewByRes);

		
		return "restaurant/detail";
	}
	
	
	//복합검색
	@RequestMapping("/resSearchList")
	public String list(@ModelAttribute("vo") resSearchListVO vo, Model model
			) {
	    List<ResSearchListDto> resSearchList = restaurantDao.resSearchList(vo);
	    model.addAttribute("resSearchList", resSearchList);
	    return "restaurant/resSearchList";
	}
	
}
