package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerBlockDto;
import com.kh.matdori.dto.CustomerDto;

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
		if (customerDto == null) {
			return "Y";
		} else {
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

	//오류?로 임시 주석처리
//	@RequestMapping("/cancel") // customerId를 받아오는 경로 지정
//	    public String cusCancel(@RequestBody CustomerBlockDto customerBlockDto) {
//	        customerDao.deleteBlock(customerBlockDto.getCustomerId()); // customerId를 사용하여 처리
//	        return "redirect:/customer/detail";
//	    }
//	
//	
//	public String cusCancel(@RequestBody CustomerBlockDto customerBlockDto) {
//		customerDao.deleteBlock(customerBlockDto.getCustomerId()); // customerId를 사용하여 처리
//		return "redirect:/customer/detail";
//	}

//	  @RequestMapping("/levelEdit")
//	    public ResponseEntity<String> customerEdit(@RequestBody CustomerDto customerDto) {
//	        boolean result = customerDao.updateCustomerLevel(customerDto);
//	        if (result) {
//	            return ResponseEntity.ok("등급이 수정되었습니다.");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등급 수정에 실패했습니다.");
//	        }
//	    }
	  
	  @RequestMapping("/levelEdit")
	    public ResponseEntity<String> customerLevelEdit(@RequestParam String customerId, @RequestParam String customerLevel) {
	      CustomerDto customerDto = customerDao.selectOne(customerId);
		  boolean result = customerDao.updateCustomerLevel(customerId, customerLevel);
	        if (result) {
	            return ResponseEntity.ok("등급이 수정되었습니다.");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등급 수정에 실패했습니다.");
	        }
	    }
	  
	  @RequestMapping("/pointEdit")
	  public ResponseEntity<String> customerPointEdit(@RequestParam String customerId, @RequestParam int customerPoint) {
	  CustomerDto customerDto = customerDao.selectOne(customerId);
	  boolean result = customerDao.updatePoint(customerId, customerPoint);
	  if(result) {  return ResponseEntity.ok("포인트가 수정되었습니다.");
      } else {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("포인트 수정에 실패했습니다.");
      }
  }
}