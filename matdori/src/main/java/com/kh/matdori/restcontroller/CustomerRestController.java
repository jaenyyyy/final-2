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
	
	
	// 아이디 중복 여부 체크 
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
	
	// 이메일 조회 
	@PostMapping("emailCheck")
	public String emailCheck(@RequestParam String customerEmail) {
	    CustomerDto customerDto = customerDao.selectOneByEmail(customerEmail);
	    if (customerDto == null) {
	        return "Y";
	    } else {
	        return "N";
	    }
	}
	
	
}
