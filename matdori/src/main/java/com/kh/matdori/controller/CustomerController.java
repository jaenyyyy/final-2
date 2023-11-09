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

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {
	 
	@Autowired
	private CustomerDao customerDao;
	
	
	// 회원가입 ok
	@GetMapping("/join")
	public String join() {
		return "customer/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute CustomerDto customerDto) {
		customerDao.insert(customerDto);
		return "redirect:joinFinish";
	}
	
	// 회원가입 완료 
	@RequestMapping("/joinFinish")
	public String joinFinish() {
		return "customer/joinFinish";
	}
	
	
	// 로그인 ok
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
	
	
	// 로그아웃 ok
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("level");
		return "redirect:/";
	}

	
	// 마이페이지 ok
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		String customerId = (String) session.getAttribute("name");
		log.debug("마이페이지 접속 = {}", customerId);
		CustomerDto customerDto = customerDao.selectOne(customerId);
		model.addAttribute("customerDto", customerDto);
		
		return "customer/mypage";
	}
	
	
	

	
//	@GetMapping("/password")
//	public String password() {
//		return "customer/password";
//	}
//	
//	@PostMapping("/password")
//	public String password(HttpSession session,
//								@RequestParam String originPw,
//								@RequestParam String changePw) {
//		String customerId = (String) session.getAttribute("name");
//		CustomerDto customerDto = customerDao.selectOne(customerId);
//		
//		if(customerDto.getCustomerPw().equals(originPw)) {
//			customerDao.updateCustomerPw(customerId, changePw);
//			return "redirect:passwordFinish";
//		}
//		else {
//			return "redirect:password?error";
//		}
//	}
//	
//	@RequestMapping("/passwordFinish")
//	public String passwordFinish() {
//		return "customer/passwordFinish";
//	}
//	
	
	
	
	// 변경 ok
	@GetMapping("/change")
	public String change(HttpSession session, Model model) {
		String customerId = (String)session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		model.addAttribute("customerDto", customerDto);
		return "customer/change";
	}
	
	@PostMapping("/change")
	public String change(@ModelAttribute CustomerDto inputDto,
										HttpSession session) {
	    String customerId = (String) session.getAttribute("name");
	    CustomerDto findDto = customerDao.selectOne(customerId);
	    if (inputDto.getCustomerPw().equals(findDto.getCustomerPw())) {
	        inputDto.setCustomerId(customerId);
	        customerDao.edit(customerId, inputDto);
	        return "redirect:mypage";
	    } 
	    else {
	        return "redirect:change?error";
	    }
	}
	
	@GetMapping("/exit")
	public String exit() {
		return "customer/exit";
	}
	
	
	@PostMapping("/exit")
	public String exit(HttpSession session, @RequestParam String customerPw) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		if(customerDto.getCustomerPw().equals(customerPw)) {
			customerDao.delete(customerId);
			session.removeAttribute("name");
			return "redirect:exitFinish";
		}
		else {
			return "redirect:exit?error";
		}
	}
		
	@RequestMapping("/exitFinish")
	public String exitFinish() {
		return "customer/exitFinish";
		}
	}
	
	


