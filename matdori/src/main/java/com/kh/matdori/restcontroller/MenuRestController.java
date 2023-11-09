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
	
	@DeleteMapping("/delete/{menuNo}")
	public void delete(@PathVariable int menuNo) {
		menuDao.delete(menuNo);
	}
	
	@GetMapping("/menulist")
	public List<MenuDto> list(){
		return menuDao.selectList();
	}
	
	@GetMapping("/{menuNo}")
	public ResponseEntity<MenuDto> find(@PathVariable int menuNo){
		MenuDto menuDto = menuDao.selectOne(menuNo);
		if (menuDto != null) {
			return ResponseEntity.ok().body(menuDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/edit/{menuNo}")
	public ResponseEntity<String> edit(@PathVariable int menuNo, @RequestBody MenuDto menuDto){
		boolean result = menuDao.edit(menuNo, menuDto);
		return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	}
	

