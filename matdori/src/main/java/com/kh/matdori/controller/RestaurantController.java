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
import com.kh.matdori.dao.MenuTypeDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.HashtagListDto;
import com.kh.matdori.dto.MenuTypeDto;
import com.kh.matdori.dto.ResSearchListDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.vo.MenuWithImagesVO;
import com.kh.matdori.vo.ResSearchListVO;

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
	@Autowired
	private MenuTypeDao menuTypeDao;
	
	@RequestMapping("/detail")
	public String detail(Model model, 
						 @RequestParam int resNo) {
		RestaurantDto resDto = restaurantDao.selectOne(resNo);
		List<MenuWithImagesVO> menuListByRes = menuDao.getMenuByRes(resNo);
		List<ReviewDto> reviewByRes = reviewDao.selectListByRes(resNo);
		List<HashtagListDto> resHashDto = hashtagDao.resHashList(resNo);
		


		model.addAttribute("resDto", resDto);
		model.addAttribute("menuListByRes", menuListByRes);
		model.addAttribute("reviewByRes", reviewByRes);		
	    // 별점 평균 조회
        double averageRating = reviewDao.getAverageRatingByRes(resNo);
        model.addAttribute("averageRating", averageRating);
		model.addAttribute("resHashDto", resHashDto);

		
		return "restaurant/detail";
	}
	
	// 해당 식당의 리뷰 리스트 가져오기
	@RequestMapping("/reviewList")
	public String reviewList(Model model, @RequestParam int resNo) {
	    List<ReviewDto> reviewListByRes = reviewDao.selectListByRes(resNo);
	    model.addAttribute("reviewListByRes", reviewListByRes);
	    
	    return "restaurant/reviewList"; // reviewList.jsp로 이동
	}

	//해당 식당의 메뉴 리스트 가져오기
	@RequestMapping("/menuList")
	public String menuList(@RequestParam("resNo") int resNo, Model model) {
	    // 메뉴 타입별로 메뉴 리스트를 저장할 구조
	    Map<MenuTypeDto, List<MenuWithImagesVO>> menuListByType = new HashMap<>();

	    // resNo에 해당하는 모든 menuTypeNo 가져오기
	    List<MenuTypeDto> menuTypes = menuTypeDao.selectListByResNo(resNo);
	    for (MenuTypeDto menuType : menuTypes) {
	        // 각 menuTypeNo에 해당하는 메뉴 리스트 가져오기
	        List<MenuWithImagesVO> menus = menuDao.selectMenuByType(menuType.getMenuTypeNo());
	        menuListByType.put(menuType, menus);
	    }

	    // Model에 메뉴 타입별 메뉴 리스트 추가
	    model.addAttribute("menuListByType", menuListByType);

	    return "restaurant/menuList"; // menuList.jsp 페이지로 이동
	}
	
	//복합검색
	@RequestMapping("/resSearchList")
	public String list(@ModelAttribute("vo") ResSearchListVO vo, Model model
			) {
	    List<ResSearchListDto> resSearchList = restaurantDao.resSearchList(vo);
	    
	    
	    model.addAttribute("resSearchList", resSearchList);
	    return "restaurant/resSearchList";
	}
	
}
