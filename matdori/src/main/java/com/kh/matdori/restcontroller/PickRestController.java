package com.kh.matdori.restcontroller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.PickDao;
import com.kh.matdori.dto.PickDto;
import com.kh.matdori.vo.PickVO;

@CrossOrigin
@RestController
@RequestMapping("/rest/pick")
public class PickRestController {
	
	@Autowired
	private PickDao pickDao;
	
	//만들어야 하는 기능
	//[1] 북마크 설정/해제를 수행하는 매핑 - 사용자가 책갈피 클릭 시 처리
	//[2] 북마크 상태를 확인하는 매핑 - 사용자 최초 화면에 표시할 책갈피 확인
	
	@RequestMapping("/check")
	public PickVO check(@ModelAttribute PickDto pickDto, HttpSession session) {
		String customerId = (String) session.getAttribute("name");
		pickDto.setCustomerId(customerId);
		
		boolean isCheck = pickDao.check(pickDto);
		int count = pickDao.count(pickDto.getResNo());
		
		PickVO vo = new PickVO();
		vo.setCheck(isCheck);
		vo.setCount(count);
		//return isCheck ? "Y" : "N";
		return vo;
	}
	
	@RequestMapping("/action")
	public PickVO action(@ModelAttribute PickDto pickDto, HttpSession session) {
		String customerId = (String) session.getAttribute("name");
		pickDto.setCustomerId(customerId);
		
		boolean isCheck = pickDao.check(pickDto);
		if(isCheck) {//북마크가 체크되 있다면
			pickDao.delete(pickDto);//북마크 해제
		}
		else {//아니면
			pickDao.insert(pickDto);//북마크 설정
		}
		int count = pickDao.count(pickDto.getResNo());
		
		PickVO vo = new PickVO();
		vo.setCheck(!isCheck);
		vo.setCount(count);
		
		return vo;
	}

}
