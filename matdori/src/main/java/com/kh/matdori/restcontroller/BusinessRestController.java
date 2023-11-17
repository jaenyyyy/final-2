package com.kh.matdori.restcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.BusinessDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.service.EmailService;

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
//	private EmailReactService emailService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	//사업자 가입
	@PostMapping("/join")
	public void insert(@RequestBody BusinessDto businessDto) throws MessagingException, IOException {
	    // 비밀번호 암호화
	    String encodedPassword = encoder.encode(businessDto.getBusPw());
	    businessDto.setBusPw(encodedPassword);
	    
	    businessDao.insert(businessDto);
	    // emailService.sendCelebration(businessDto.getBusEmail());
	}


	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody BusinessDto businessDto) {
	    String busId = businessDto.getBusId();
	    String busPw = businessDto.getBusPw();

	    BusinessDto storedBusiness = businessDao.selectOne(busId);

	    if (storedBusiness != null && encoder.matches(busPw, storedBusiness.getBusPw())) {
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
	
    @PutMapping("/changeInfo/busId/{busId}")
    public ResponseEntity<String> edit(
            @PathVariable String busId, @RequestBody BusinessDto businessDto) {
        // 비밀번호 암호화
    	System.out.println(businessDto.getBusPw());
    	System.out.println(businessDto.getBusId());
    	
        String encryptedPassword = encoder.encode(businessDto.getBusPw());
        businessDto.setBusPw(encryptedPassword);

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


	//아이디 중복체크
	@GetMapping("/check/{busId}")
	public ResponseEntity<Map<String, Boolean>> checkBusinessId(@PathVariable String busId) {
	    BusinessDto existingBusiness = businessDao.selectOne(busId);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("exists", existingBusiness != null);
	    return ResponseEntity.ok().body(response);
	}
	
	//사업자 등록번호 중복체크
	@GetMapping("/checkRegNo/{busRegNo}")
	public ResponseEntity<Map<String, Boolean>> checkBusinessRegNo(@PathVariable String busRegNo) {
	    BusinessDto existingBusiness = businessDao.selectOneRegNo(busRegNo);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("exists", existingBusiness != null);
	    return ResponseEntity.ok().body(response);
	}
	
	
	
	
	//아이디 찾기(사업자번호와 비밀번호
	@PostMapping("/findId/busregno/{busRegNo}/buspw/{busPw}")
	public ResponseEntity<String> findIdByRegNoAndPw(@PathVariable String busRegNo, @PathVariable String busPw) {
	    System.out.println("busRegNo: " + busRegNo);
	    System.out.println("busPw: " + busPw);
	    
	    BusinessDto foundBusiness = businessDao.findByRegNo(busRegNo);
	    
	    if (foundBusiness != null && busPw.equals(foundBusiness.getBusPw())) {
	        System.out.println("일치");
	        return ResponseEntity.ok(foundBusiness.getBusId());
	    } else {
	        System.out.println("불일치");
	        return ResponseEntity.notFound().build();
	    }
	}




	
	//사업자 개인정보 변경
	
	
}
