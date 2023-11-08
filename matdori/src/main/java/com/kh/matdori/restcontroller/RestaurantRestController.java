package com.kh.matdori.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.RestaurantDto;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantRestController {

	@Autowired
	private RestaurantDao restaurantDao;
	
	@GetMapping("/")
	public void insert(@RequestBody RestaurantDto restaurantDto) {
		restaurantDao.insert(restaurantDto);
	}
	
	@DeleteMapping("/{resNo}")
	public void delete(@PathVariable int resNo) {
		restaurantDao.delete(resNo);
	}
	
	
	

}
