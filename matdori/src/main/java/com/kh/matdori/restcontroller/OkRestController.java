package com.kh.matdori.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.OkDao;
import com.kh.matdori.dto.OkDto;

@CrossOrigin
@RestController
@RequestMapping("/ok")
public class OkRestController {
	@Autowired
	private OkDao okDao;
	
	@PostMapping("/")
	public void insert(@RequestBody OkDto okDto) {
		okDao.insert(okDto);
	}

	@PutMapping("/{okNo}")
	public void update(@RequestBody OkDto okDto, @PathVariable int okNo) {
		okDao.edit(okNo, okDto);
	}
	
	@DeleteMapping("/{okNo}")
	public void delete(@PathVariable int okNo) {
		okDao.delete(okNo);
	}
	@GetMapping("/")
	public List<OkDto> seatListByclock(){
		return okDao.selectList();
	}
	
}
