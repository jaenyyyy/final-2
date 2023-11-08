package com.kh.matdori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.matdori.dao.QnaDao;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QnaDao qnaDao;
	
	@GetMapping("write")
	public String write() {
		return "qna/write";
	}
	

}
