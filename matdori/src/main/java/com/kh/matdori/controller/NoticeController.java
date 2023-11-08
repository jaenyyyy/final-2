package com.kh.matdori.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.matdori.dao.NoticeDao;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.NoticeDto;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeDao noticeDao;
	
	@GetMapping("write")
	public String write() {
		return "notice/write";
	}
	
//	@PostMapping("write")
//	public String write(@ModelAttribute NoticeDto noticeDto, HttpSession session,
//						@ModelAttribute CustomerDto customerDto) {
//		
//		int noticeNo = main
//		noticeDao.insert(noticeDto);
//		return "redirect:detail";
//	}
}
