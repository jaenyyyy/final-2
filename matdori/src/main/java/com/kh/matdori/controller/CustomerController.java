
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
	
	
	// 회원가입 ok 
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
	
    
	// 회원가입 완료 ok
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
	
	
	
	
	
	// 개인정보 변경 ok
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

	    // 사용자가 입력한 비밀번호를 암호화
	    String encryptedInputPassword = encoder.encode(inputDto.getCustomerPw());

	    // 암호화된 입력 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
	    if (encoder.matches(inputDto.getCustomerPw(), findDto.getCustomerPw())) {
	        // 암호화된 비밀번호를 DTO에 설정
	        inputDto.setCustomerPw(encryptedInputPassword);

	        inputDto.setCustomerId(customerId);
	        customerDao.edit(customerId, inputDto);
	        return "redirect:mypage";
	    } else {
	        return "redirect:change?error";
	    }
	}
	

	@GetMapping("/findPw")
	public String findPw() {
		return "customer/findPw";
	}
	
	
	@PostMapping("/findPw")
	public String findPw(@ModelAttribute CustomerDto customerDto) {
		//[1] 아이디로 모든 정보를 불러오고
		CustomerDto findDto = 
				customerDao.selectOne(customerDto.getCustomerId());
		//[2] 이메일이 일치하는지 확인한다
		boolean isValid = findDto != null 
				&& findDto.getCustomerEmail().equals(customerDto.getCustomerId());
		if(isValid) {//이메일이 같다면
			//이메일 발송 코드
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(findDto.getCustomerEmail());
			message.setSubject("비밀번호 찾기 결과");
			message.setText(findDto.getCustomerPw());
			sender.send(message);
			
			return "redirect:findPwFinish";
		}
		else {//이메일이 다르다면
			return "redirect:findPw?error";
		}
		
	}
	
	
	// 비밀번호 재설정 ok 
	@GetMapping("/changePw")
	public String changePw() {
	    return "customer/changePw";
	}

	@PostMapping("/changePw")
	public String changePw(HttpSession session,
	                        @RequestParam String originPw,
	                        @RequestParam String changePw,
	                        Model model) {
	    String customerId = (String) session.getAttribute("name");
	    CustomerDto findDto = customerDao.selectOne(customerId);

	    // 암호화된 입력 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
	    if (encoder.matches(originPw, findDto.getCustomerPw())) {
	        // 새로운 비밀번호를 암호화
	        String encryptedNewPassword = encoder.encode(changePw);

	        // 암호화된 비밀번호를 DTO에 설정
	        findDto.setCustomerPw(encryptedNewPassword);

	        // customerDao.edit 메소드가 새로운 비밀번호를 업데이트할 수 있도록 수정 필요
	        customerDao.edit(customerId, findDto);

	        // 비밀번호 변경 완료 후 세션 무효화 및 로그아웃
	        session.invalidate();

	        return "redirect:changePwFinish";
	    } else {
	        model.addAttribute("error", "비밀번호 변경에 실패했습니다. 입력한 비밀번호를 확인하세요.");
	        return "customer/changePw";
	    }
	}


	// 비밀번호 변경 ok 
	@RequestMapping("/changePwFinish")
	public String changePwFinish() {
	    return "customer/changePwFinish";
	}


	// 회원 탈퇴 ok 
	@GetMapping("/exit")
	public String exit() {
		return "customer/exit";
	}
	
	
	@PostMapping("/exit")
	public String exit(HttpSession session, @RequestParam String customerPw) {
	    String customerId = (String) session.getAttribute("name");
	    CustomerDto customerDto = customerDao.selectOne(customerId);
	 // 사용자가 입력한 비밀번호를 암호화
	    String encryptedInputPassword = encoder.encode(customerPw);

	    if (encoder.matches(customerPw, customerDto.getCustomerPw())) {
	        // 삭제할 때도 암호화된 비밀번호를 사용
	        customerDao.delete(customerId);
	        session.removeAttribute("name");
	        return "redirect:exitFinish";
	    } else {
	        return "redirect:exit?error";
	    }
	}
	    
	    
	@RequestMapping("/exitFinish")
	public String exitFinish() {
		return "customer/exitFinish";
		}
	

	
}


