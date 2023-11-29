package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.AdminDao;
import com.kh.matdori.dto.HashtagDto;
import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantJudgeDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {

	@Autowired
	private AdminDao adminDao;

	// 레스토랑 차단 기능
	@RequestMapping("/restaurant/block")
	public String resBlock(@RequestBody RestaurantBlockDto restaurantBlockDto) {
		adminDao.insertResBlock(restaurantBlockDto);
		return "redirect:/admin/restaurant/detail";
	}

	// 레스토랑 차단 해제
	@RequestMapping("/restaurant/cancel")
	public String resCancel(@RequestBody RestaurantBlockDto restaurantBlockDto) {
		adminDao.deleteResBlock(restaurantBlockDto.getResNo());
		return "redirect:/admin/restaurant/detail";
	}

	// 레스토랑 심사 수정 (승인 / 거절로 업데이트)
	@RequestMapping("/restaurant/judge")
	public void updateJudge(@RequestBody RestaurantJudgeDto restaurantJudgeDto) {
		adminDao.updateResJudge(restaurantJudgeDto);
	}

	// 해시태그 추가 기능
	@PostMapping("/hashtag/insert")
	public void insert(@ModelAttribute HashtagDto hashtagDto) {
		int hashNo = adminDao.hashSequence();
		hashtagDto.setHashNo(hashNo);
		adminDao.insertHashtag(hashtagDto);
	}

	// 해시태그 삭제 기능
	@PostMapping("/hashtag/delete")
	public void delete(@RequestParam int hashNo) {
		adminDao.deleteHashtag(hashNo);

	}

	@GetMapping("/restaurant/judge/{resNo}")
		public RestaurantJudgeDto find(@PathVariable int resNo) {
			return adminDao.selectOne(resNo);
		}

}
