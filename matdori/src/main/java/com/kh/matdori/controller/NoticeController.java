package com.kh.matdori.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.NoticeDao;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.NoticeDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.PaginationVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeDao noticeDao;
	
	@GetMapping("/write") 
	public String write() {
		return "notice/write";
	}
	
	@PostMapping("/write") 
	public String write(@ModelAttribute NoticeDto noticeDto, HttpSession session,
						@ModelAttribute CustomerDto customerDto) {
		
		int noticeNo = noticeDao.sequence();
		noticeDto.setNoticeNo(noticeNo);
		
		String customerId = (String) session.getAttribute("name");
		
		noticeDto.setNoticeWriter(customerId);
		
		noticeDao.insert(noticeDto);
	
		return "redirect:detail?noticeNo="+noticeNo;
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int noticeNo, Model model, HttpSession session) {
		NoticeDto noticeDto = noticeDao.selectOne(noticeNo);
		model.addAttribute("noticeDto", noticeDto);
		return "notice/detail";
	}
	
	@RequestMapping("/list")
	public String list(@ModelAttribute(name="vo") PaginationVO vo,Model model) {
		int count = noticeDao.countList(vo);
		vo.setCount(count);
		model.addAttribute("vo", vo);
		
		List <NoticeDto> list = noticeDao.selectList(vo);
		model.addAttribute("list", list);
		
		return "notice/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int noticeNo, Model model) {
		NoticeDto noticeDto = noticeDao.selectOne(noticeNo);
		model.addAttribute("noticeDto", noticeDto);
		return "notice/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute NoticeDto noticeDto) {
		boolean result = noticeDao.edit(noticeDto);
		if(result) return "redirect:detail?noticeNo="+noticeDto.getNoticeNo();
		else throw new NoTargetException();
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int noticeNo) {
		boolean result = noticeDao.delete(noticeNo);
		if(result) return "redirect:list";
		else throw new NoTargetException("존재하지 않는 글");
	}
}
