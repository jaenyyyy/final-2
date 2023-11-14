package com.kh.matdori.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerDto;

@CrossOrigin
@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestParam String customerId) {
		CustomerDto customerDto = customerDao.selectOne(customerId);
		if(customerDto == null) {
			return "Y";
		}
		else {
			return "N";
		}
		
	}
	
	
	@PostMapping("emailCheck")
	public String emailCheck(@RequestParam String customerEmail) {
	    CustomerDto customerDto = customerDao.selectOneByEmail(customerEmail);
	    if (customerDto == null) {
	        return "Y";
	    } else {
	        return "N";
	    }
	}
	
	
	@GetMapping("/changePw")
	public String changePw() {
	    return "customer/changePw";
	}

	@PostMapping("/changePw")
	public String changePw(HttpSession session,
	                        @RequestParam String originPw,
	                        @RequestParam String changePw) {
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
	        return "redirect:changePw?error";
	    }
	}

	@RequestMapping("/changePwFinish")
	public String changePwFinish() {
	    return "customer/changePwFinish";
	}

}
