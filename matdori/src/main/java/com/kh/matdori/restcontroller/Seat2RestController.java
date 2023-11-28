package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.Seat2Dao;
import com.kh.matdori.dto.Seat2Dto;

@CrossOrigin
@RestController
@RequestMapping("/seat2")
public class Seat2RestController {
	@Autowired
	private Seat2Dao seat2Dao;
	
	@PostMapping("/")
	public void insert(@RequestBody Seat2Dto seat2Dto) {
		seat2Dao.insert2(seat2Dto);
	}
	
	@PutMapping("/{seat2No}")
	public void update(@RequestBody Seat2Dto seat2Dto, @PathVariable int seat2No) {
		seat2Dao.edit2(seat2No, seat2Dto);
	}
	
	@PatchMapping("/{seat2No}")
	public void update2(@RequestBody Seat2Dto seat2Dto, @PathVariable int seat2No) {
		seat2Dao.edit2(seat2No, seat2Dto);
	}

	@DeleteMapping("/{seat2No}")
	public void delete(@PathVariable int seat2No) {
		seat2Dao.delete2(seat2No);
	}
	
//	@GetMapping("/")
//	public List<SeatListByResVO> seatListByRes(){
//		return seatDao.selectList();
//	}
}
