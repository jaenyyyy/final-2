package com.kh.matdori.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.BusinessDao;
import com.kh.matdori.dto.BusinessDto;

@CrossOrigin
@RestController
@RequestMapping("/business")
public class BusinessRestController {
	
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	//사업자 가입
	@PostMapping("/join")
	public void insert(@RequestBody BusinessDto businessDto) {
		businessDao.insert(businessDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody BusinessDto businessDto) {
	    String busId = businessDto.getBusId();
	    String busPw = businessDto.getBusPw();
	    
	    BusinessDto storedBusiness = businessDao.selectOne(busId); // 데이터베이스에서 사용자 정보 가져오기

	    if (storedBusiness != null && busPw.equals(storedBusiness.getBusPw())) {
	        // 로그인 성공
	        Map<String, Object> response = new HashMap<>();
	        response.put("busId", storedBusiness.getBusId()); // Adding busId to the response
	        response.put("message", "로그인 성공"); // Optionally, a success message
	        return ResponseEntity.ok(response);
	    } else {
	        // 로그인 실패
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다");
	        return ResponseEntity.status(401).body(response);
	    }
	}

	
//	public ResponseEntity<String> login(@RequestBody BusinessDto businessDto) {
//	    String busId = businessDto.getBusId();
//	    String busPw = businessDto.getBusPw();
//	    
//	    BusinessDto storedBusiness = businessDao.selectOne(busId); // 데이터베이스에서 사용자 정보 가져오기
//
//	    if (storedBusiness != null) {
//	        // 저장된 해시된 비밀번호와 사용자가 입력한 비밀번호를 비교
//	        if (passwordEncoder.matches(busPw, storedBusiness.getBusPw())) {
//	            // 로그인 성공
//	            return ResponseEntity.ok("로그인 성공");
//	        } else {
//	            // 비밀번호가 일치하지 않는 경우
//	            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다");
//	        }
//	    } else {
//	        // 사용자가 존재하지 않는 경우
//	        return ResponseEntity.status(401).body("로그인 실패: 사용자가 존재하지 않습니다");
//	    }
//	}

	
	
	//사업자 마이페이지 조회
	@GetMapping("/mypage/busId/{busId}")
	public ResponseEntity<BusinessDto> find(@PathVariable String busId){
		BusinessDto businessDto = businessDao.selectOne(busId);
		if(businessDto != null) {
			return ResponseEntity.ok().body(businessDto);		
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//사업자 정보 수정
	@PutMapping("/changeInfo/busId/{busId}")
	public ResponseEntity<String> edit(
				@PathVariable String busId, @RequestBody BusinessDto businessDto){
		boolean result = businessDao.update(busId, businessDto);
		return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	//사업자 탈퇴
	@DeleteMapping("/{busId}")
	public ResponseEntity<String> delete(@PathVariable String busId){
		boolean result = businessDao.delete(busId);
		if(result) {
			return ResponseEntity.status(200).build();
		}
		else {
			return ResponseEntity.status(404).build();
		}
	}
	
	//사업자 비밀번호 변경
	//아이디 배우고 고민해봅시다
	
}
