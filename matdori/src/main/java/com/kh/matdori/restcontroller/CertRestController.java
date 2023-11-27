package com.kh.matdori.restcontroller;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
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

		        return "customer/changePw";
		    } else {
		        model.addAttribute("error", "비밀번호 변경에 실패했습니다. 입력한 비밀번호를 확인하세요.");
		        return "redirect:changePw?error";
		    }
		}
		
		
		


		// 비밀번호 변경 ok 
		@RequestMapping("/changePwFinish")
		public String changePwFinish() {
		    return "customer/changePwFinish";
		}

}
	
	
	
	
