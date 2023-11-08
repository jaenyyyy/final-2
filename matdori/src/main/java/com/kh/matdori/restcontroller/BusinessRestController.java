package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//사업자 가입
	@PostMapping("/join")
	public void insert(@RequestBody BusinessDto businessDto) {
		businessDao.insert(businessDto);
	}
	
	//사업자 마이페이지 조회
	@GetMapping("/mypage/{busId}")
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
	@PutMapping("/changeInfo/{busId}")
	public ResponseEntity<String> edit(
			@PathVariable String busId, @RequestBody BusinessDto businessDto){
		boolean result = businessDao.edit(busId, businessDto);
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
	
}
