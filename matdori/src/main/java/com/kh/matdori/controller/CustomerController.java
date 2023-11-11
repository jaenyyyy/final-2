
package com.kh.matdori.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.CertDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CertDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.service.EmailService;


import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private CertDao certDao;
	
	
    @GetMapping("/join")
    public String join() {
        return "customer/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute CustomerDto customerDto) throws MessagingException, IOException {
        customerDao.insert(customerDto);
        emailService.sendCelebration(customerDto.getCustomerEmail());
        return "redirect:joinFinish";
    }
	
	// 회원가입 완료 
	@GetMapping("/joinFinish")
	public String joinFinish() {
		return "customer/joinFinish";
	}
	
	
	// 로그인 ok
	@GetMapping("/login")
	public String login() {
		return "customer/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute CustomerDto dto, HttpSession session) {
		CustomerDto target = customerDao.login(dto);
		if(target == null) {
			return "redirect:login?error";
		}
		else {
			//세션 정보 설정...후 메인페이지 혹은 기존페이지로 이동
			 session.setAttribute("name", target.getCustomerId());
	         session.setAttribute("level", target.getCustomerLevel());
			return "redirect:/";
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
	
	
	

	@GetMapping("/password")
	public String password() {
		return "customer/password";
	}
	
	@PostMapping("/password")
	public String password(HttpSession session,
								@RequestParam String originPw,
								@RequestParam String changePw) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		
		if(customerDto.getCustomerPw().equals(originPw)) {
			customerDao.updateCustomerPw(customerId, changePw);
			return "redirect:passwordFinish";
		}
		else {
			return "redirect:password?error";
		}
	}
	
	@RequestMapping("/passwordFinish")
	public String passwordFinish() {
		return "customer/passwordFinish";
	}
	
	
	
	
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
	
//	//비밀번호 찾기
//	@GetMapping("/findPw")
//	public String findPw() {
//		return "customer/findPw.jsp";
//	}
//	
//	
//	@PostMapping("/findPw")
//	public String findPw(@ModelAttribute CustomerDto customerDto) {
//		//[1] 아이디로 모든 정보를 불러오고
//		CustomerDto findDto = 
//				customerDao.selectOne(customerDto.getCustomerId());
//		//[2] 이메일이 일치하는지 확인한다
//		boolean isValid = findDto != null 
//				&& findDto.getCustomerEmail().equals(customerDto.getCustomerId());
//		if(isValid) {//이메일이 같다면
//			//이메일 발송 코드
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo(findDto.getCustomerEmail());
//			message.setSubject("비밀번호 찾기 결과");
//			message.setText(findDto.getCustomerPw());
//			sender.send(message);
//			
//			return "redirect:findPwFinish";
//		}
//		else {//이메일이 다르다면
//			return "redirect:findPw?error";
//		}
//		
//	}
//	
//	@RequestMapping("/findPwFinish")
//	public String findPwFinish() {
//		return "customer/findPwFinish.jsp";
//	}
	
	
//	// 이메일 인증번호 
//	@PostMapping("/send")
//	public void send(@RequestParam String certEmail) {
//		Random r = new Random();
//		int number = r.nextInt(1000000);
//		DecimalFormat fm = new DecimalFormat("000000");
//		String certNumber = fm.format(number);
//		
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(certEmail);
//		message.setSubject("[맛도리] 인증번호 안내");
//		message.setText("인증번호는 [" + certNumber + "] 입니다");
//		sender.send(message);
//		
//		certDao.delete(certEmail);
//		CertDto certDto = new CertDto();
//		certDto.setCertEmail(certEmail);
//		certDto.setCertNumber(certNumber);
//		certDao.insert(certDto);
//	}
//	
//	
//	@PostMapping("/check")
//	public Map<String, Object> check(@ModelAttribute CertDto certDto) {
//		//[1] 이메일로 인증정보를 조회
//		CertDto findDto = certDao.selectOne(certDto.getCertEmail());//기간제한없음
//		
//		if(findDto != null) {
//			//[2] 인증번호 비교
//			boolean isValid = 
//					findDto.getCertNumber().equals(certDto.getCertNumber());
//			if(isValid) {
//				//인증 성공하면 인증번호를 삭제
//				certDao.delete(certDto.getCertEmail());
//				return Map.of("result", true);
//			}
//		}
//		
//		return Map.of("result", false);
	}





