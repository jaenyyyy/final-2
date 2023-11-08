package com.kh.matdori.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {
	 
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/join")
	public String join() {
		return "customer/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute CustomerDto customerDto) {
		customerDao.insert(customerDto);
		return "redirect:joinFinish";
	}
	
	@RequestMapping("/joinFinish")
	public String joinFinish() {
		return "customer/joinFinish";
	}
	
	
	
	@GetMapping("/login")
	public String login() {
		return "customer/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute CustomerDto inputDto, HttpSession session) {
	    CustomerDto findDto = customerDao.selectOne(inputDto.getCustomerId());
	    if (findDto == null) {
	        return "redirect:login?error";
	    }
	    boolean isCorrectPw = inputDto.getCustomerPw().equals(findDto.getCustomerPw());

	    if (isCorrectPw) {
	        session.setAttribute("name", findDto.getCustomerId());
	        return "redirect:/";
	    } else {
	        return "redirect:login?error";
	    }
	}
	
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("level");
		return "redirect:/";
	}

	
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		String customerId = (String) session.getAttribute("name");
		log.debug("마이페이지 접속 = {}", customerId);
		CustomerDto customerDto = customerDao.selectOne(customerId);
		model.addAttribute("customerDto", customerDto);
		
		return "customer/mypage";
	}
	
	
	@GetMapping("/change")
	public String change(HttpSession session, Model model) {
		String customerId = (String)session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		model.addAttribute("customerDto", customerDto);
		return "customer/change";
	}
	
	@PostMapping("/change")
	public String change(@ModelAttribute CustomerDto inputDto, HttpSession session) {
	    String customerId = (String) session.getAttribute("name");
	    CustomerDto findDto = customerDao.selectOne(customerId);
	    if (inputDto.getCustomerPw().equals(findDto.getCustomerPw())) {
	        inputDto.setCustomerId(customerId);
	        customerDao.edit(customerId, inputDto);
	        return "redirect:mypage";
	    } else {
	        return "redirect:change?error";
	    }
	}
	

}
