package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dto.MenuDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuRestController {
	
	@Autowired
	private MenuDao menuDao;

	@PostMapping("/")
	public void insert(@RequestBody MenuDto menuDto) {
		menuDao.save(menuDto);
	}
	
	@DeleteMapping("/{menuNo}")
	public void delete(@PathVariable int menuNo) {
		menuDao.delete(menuNo);
	}
	
}
