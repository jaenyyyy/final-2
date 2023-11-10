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
import com.kh.matdori.error.NoTargetException;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QnaDao qnaDao;
	
	
	@GetMapping("/write")
	public String write() {
		return "qna/write";
	}
	
	
	@PostMapping("/write") //작성
	public String write(@ModelAttribute QnaDto qnaDto, HttpSession session,
						@ModelAttribute CustomerDto customerDto) {
		int qnaNo = qnaDao.sequence();
		qnaDto.setQnaNo(qnaNo);
		
		String customerId = (String) session.getAttribute("name");
		
		qnaDto.setQnaWriter(customerId);
		
		qnaDao.insert(qnaDto);
	
		return "redirect:detail?qnaNo="+qnaNo;
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int qnaNo, Model model, HttpSession session) {
		QnaDto qnaDto = qnaDao.selectOne(qnaNo);
		model.addAttribute("qnaDto", qnaDto);
		return "qna/detail";
	}
	
	@RequestMapping("/list")
	public String list(@ModelAttribute QnaDto qnaDto, Model model) {
		model.addAttribute("list", qnaDao.selectList());
		return "qna/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int qnaNo, Model model) {
		QnaDto qnaDto = qnaDao.selectOne(qnaNo);
		model.addAttribute("qnaDto", qnaDto);
		return "notice/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam int qnaNo) {
		boolean result = qnaDao.delete(qnaNo);
		if(result) return "redirect:list";
		else throw new NoTargetException("존재하지 않는 글번호");
	}
}
