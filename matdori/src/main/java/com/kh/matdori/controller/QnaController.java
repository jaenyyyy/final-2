package com.kh.matdori.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.QnaDao;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.QnaDto;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QnaDao qnaDao;
	
	
	@GetMapping("write")
	public String write() {
		return "qna/write";
	}
	
	
	@PostMapping("write") //작성
	public String write(@ModelAttribute QnaDto qnaDto, HttpSession session,
						@ModelAttribute CustomerDto customerDto) {
		int qnaNo = qnaDao.sequence();
		qnaDto.setQnaNo(qnaNo);
		
		String customerId = (String) session.getAttribute("name");
		
		qnaDto.setQnaWriter(customerId);
		
		qnaDao.insert(qnaDto);
	
		return "redirect:detail?qnaNo="+qnaNo;
	}
	
	
//	@RequestMapping("/detail") //상세
//	public String detail(@RequestParam int qnaNo, Model model, HttpSession session) {
//		QnaDto qnaDto = qnaDao.select
//	}

}
