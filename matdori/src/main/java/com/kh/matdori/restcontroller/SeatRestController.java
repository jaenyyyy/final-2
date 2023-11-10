package com.kh.matdori.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.kh.matdori.dao.SeatDao;
import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.vo.SeatListByResVO;

@CrossOrigin
@RestController
@RequestMapping("/seat")
public class SeatRestController {
	@Autowired
	private SeatDao seatDao;
	
	@PostMapping("/")
	public void insert(@RequestBody SeatDto seatDto) {
		seatDao.insert(seatDto);
	}
	
	@PutMapping("/{seatNo}")
	public void update(@RequestBody SeatDto seatDto, @PathVariable int seatNo) {
		seatDao.edit(seatNo, seatDto);
	}
	
	@PatchMapping("/{seatNo}")
	public void update2(@RequestBody SeatDto seatDto, @PathVariable int seatNo) {
		seatDao.edit(seatNo, seatDto);
	}

	@DeleteMapping("/{seatNo}")
	public void delete(@PathVariable int seatNo) {
		seatDao.delete(seatNo);
	}
	
	@GetMapping("/")
	public List<SeatListByResVO> seatListByRes(){
		return seatDao.selectList();
	}
}
