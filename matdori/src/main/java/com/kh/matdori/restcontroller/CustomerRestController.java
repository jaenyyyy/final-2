package com.kh.matdori.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerAdminListDto;
import com.kh.matdori.dto.CustomerBlockDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.RestaurantBlockDto;

@CrossOrigin
@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	
	// 이메일 인증번호 발송 controller 
	
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
	
	// 이용자 차단 구문 
	@RequestMapping("/block") // customerId를 받아오는 경로 지정
	    public String cusBlock(@RequestBody CustomerBlockDto customerBlockDto) {
	        customerDao.insertCusBlock(customerBlockDto); // customerId를 사용하여 처리
	        return "redirect:/customer/detail";
	    }

	@RequestMapping("/cancel") // customerId를 받아오는 경로 지정
	    public String cusCancel(@RequestBody CustomerBlockDto customerBlockDto) {
	        customerDao.deleteBlock(customerBlockDto.getCustomerId()); // customerId를 사용하여 처리
	        return "redirect:/customer/detail";
	    }
	}


