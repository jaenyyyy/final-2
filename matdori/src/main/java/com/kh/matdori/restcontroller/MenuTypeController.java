package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.MenuTypeDao;
import com.kh.matdori.dto.MenuTypeDto;



@CrossOrigin
@RestController
@RequestMapping("/menuType")
public class MenuTypeController {

	@Autowired
	private MenuTypeDao menuTypeDao;
	
	@PostMapping("/save")
	public void insert(@RequestBody MenuTypeDto menuTypeDto) {
		menuTypeDao.insert(menuTypeDto);
	}
	
	
	@DeleteMapping("/delete/{menuTypeNo}")
	public void delete(@PathVariable int menuTypeNo) {
		menuTypeDao.delete(menuTypeNo);
	}
}
