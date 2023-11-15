package com.kh.matdori.restcontroller;

import java.util.HashMap;
import java.util.List;
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
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.RestaurantDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin
@RestController
@RequestMapping("/business")
public class BusinessRestController {
	
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	private RestaurantDto restaurantDto;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
	
	//사업자 가입
	@PostMapping("/join")
	public void insert(@RequestBody BusinessDto businessDto) {
		businessDao.insert(businessDto);
	}	

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody BusinessDto businessDto) {
	    String busId = businessDto.getBusId();
	    String busPw = businessDto.getBusPw();

	    BusinessDto storedBusiness = businessDao.selectOne(busId);

	    if (storedBusiness != null && busPw.equals(storedBusiness.getBusPw())) {
	        // 로그인 성공

	        // JWT 생성
	        String token = Jwts.builder()
	                .setSubject(busId)
	                .signWith(SignatureAlgorithm.HS512, "khproject@lnyy4fh)3h@wg0n)sw6nsynyk!xbxq7f2!%iry+3w1&loos$$finall")
	                .compact();

	        // 클라이언트에게 busId와 token을 함께 반환
	        Map<String, String> loginInfo = new HashMap<>();
	        loginInfo.put("busId", busId);
	        loginInfo.put("token", token);
	        

	        return ResponseEntity.ok(loginInfo);
	    } else {
	        // 로그인 실패
	        return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다");
	    }
	}



	
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
	
	// 사업자가 운영 중인 매장목록 조회
	@GetMapping("/myres/{busId}")
	public ResponseEntity<List<RestaurantDto>> getMyRestaurantList(@PathVariable String busId) {
	    List<RestaurantDto> myRestaurantList = businessDao.getMyRestaurantList(busId);
	    if (myRestaurantList != null && !myRestaurantList.isEmpty()) {
	        return ResponseEntity.ok().body(myRestaurantList);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}



	
	//사업자 비밀번호 변경
	//아이디 배우고 고민해봅시다
	
}
