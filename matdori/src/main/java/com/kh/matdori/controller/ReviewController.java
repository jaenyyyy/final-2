package com.kh.matdori.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.ReviewDto;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private AttachDao attachDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
	//매장에 나오는 후기 목록  (나중에 레스토랑으로 옮길 것)
	@RequestMapping("/listByRes")
	public String list(@ModelAttribute RestaurantDto restaurantDto,
						Model model, @ModelAttribute ReviewDto reviewDto) {
		int resNo = restaurantDto.getResNo();
		reviewDto.setReviewNo(resNo);
		
		List <ReviewDto> listByRes = reviewDao.selectListByRes(resNo);
		model.addAttribute("listByRes", listByRes);
		
		return "review/list";
	}
	
}
