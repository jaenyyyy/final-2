package com.kh.matdori.restcontroller;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.CertDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CertDto;
import com.kh.matdori.dto.CustomerDto;

@CrossOrigin
@RestController
@RequestMapping("/rest/cert")
public class CertRestController {
	
	@Autowired
	private CertDao certDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping("/send")
	public void send(@RequestParam String certEmail) {
		//[1] 인증번호 생성
		Random r = new Random();
		int number = r.nextInt(1000000);
		DecimalFormat fm = new DecimalFormat("000000");
		String certNumber = fm.format(number);
		
		//[2] 이메일 발송
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(certEmail);
		message.setSubject("[맛도리] 인증번호 안내");
		message.setText("인증번호는 [" + certNumber + "] 입니다");
		sender.send(message);
		
		//[3] DB에 저장(기존 데이터 삭제)
		certDao.delete(certEmail);
		CertDto certDto = new CertDto();
		certDto.setCertEmail(certEmail);
		certDto.setCertNumber(certNumber);
		certDao.insert(certDto);
	}
	
	//인증번호 검사
	@PostMapping("/check")
	public Map<String, Object> check(@ModelAttribute CertDto certDto) {
		//[1] 이메일로 인증정보를 조회
		CertDto findDto = certDao.selectOne(certDto.getCertEmail());//기간제한없음
		
		if(findDto != null) {
			//[2] 인증번호 비교
			boolean isValid = 
					findDto.getCertNumber().equals(certDto.getCertNumber());
			if(isValid) {
				//인증 성공하면 인증번호를 삭제
				certDao.delete(certDto.getCertEmail());
				return Map.of("result", true);
			}
			
				
		}
		
		return Map.of("result", false);
	}

	
	@GetMapping("/change")
	public String change(HttpSession session, Model model) {
	    String customerId = (String) session.getAttribute("name");
	    CustomerDto customerDto = customerDao.selectOne(customerId);
	    model.addAttribute("customerDto", customerDto);
	    return "customer/change";
	}

	@PostMapping("/change")
	public String change(@ModelAttribute CustomerDto inputDto, HttpSession session) {
	    String customerId = (String) session.getAttribute("name");
	    CustomerDto findDto = customerDao.selectOne(customerId);

	    // 비밀번호 암호화
	    String encryptedInputPassword = encoder.encode(inputDto.getCustomerPw());
	    
	    if (encoder.matches(inputDto.getCustomerPw(), findDto.getCustomerPw())) {
	        // 일치하면 새로운 비밀번호로 업데이트
	        findDto.setCustomerPw(encryptedInputPassword);
	        
	        // 데이터베이스 업데이트
	        customerDao.edit(customerId, findDto);

	        return "redirect:mypage";
	    } else {
	        // 일치하지 않으면 변경 실패
	        return "redirect:change?error";
	    }
	}


	}
	
	
