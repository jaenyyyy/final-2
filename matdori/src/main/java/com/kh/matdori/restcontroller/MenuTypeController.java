package com.kh.matdori.restcontroller;

import java.util.List;

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
	
	@PutMapping("/update/{menuTypeNo}")
	public ResponseEntity<String> edit(@PathVariable int menuTypeNo, @RequestBody MenuTypeDto menuTypeDto){
		boolean result=menuTypeDao.edit(menuTypeNo, menuTypeDto);
		return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
//	@GetMapping("/")
//	public ResponseEntity<List<MenuTypeDto>> getMenuType(@PathVariable("menuTypeNo") Integer menuTypeNo){
//		if(menuTypeNo ==null) {
//			return ResponseEntity.badRequest().build();
//		}
//		List <MenuTypeDto> menuTypeList = menuTypeDao.selectList(menuTypeNo);
//		return ResponseEntity.ok(menuTypeList);
//	}
	@GetMapping("/list/{resNo}")
	public List<MenuTypeDto> list(@PathVariable String resNo){
	    return menuTypeDao.selectListByResNo(resNo);
	}
	}
