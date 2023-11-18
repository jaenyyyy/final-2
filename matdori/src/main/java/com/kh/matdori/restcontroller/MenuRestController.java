package com.kh.matdori.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dto.MenuByResDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.vo.MenuWithImagesVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

//	@GetMapping("/list/{resNo}")
//	public ResponseEntity<List<MenuByResDto>> getMenuListByRestaurant(@PathVariable("resNo") Integer resNo){
//	    if (resNo == null) {
//	        // resNo가 null일 경우 적절한 예외 처리
//	        return ResponseEntity.badRequest().build();
//	    }
//	    List<MenuByResDto> menuList = menuDao.selectList(resNo);
//	    return ResponseEntity.ok(menuList);
//	}

	@GetMapping("/{menuNo}")
	public ResponseEntity<MenuByResDto> find(@PathVariable int menuNo) {
		MenuByResDto menuByResDto = menuDao.selectOne(menuNo);
		if (menuByResDto != null) {
			return ResponseEntity.ok().body(menuByResDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{menuNo}")
	public ResponseEntity<String> edit(@PathVariable int menuNo, @RequestBody MenuDto menuDto) {
		boolean result = menuDao.edit(menuNo, menuDto);
		log.debug("menuDto={}", menuDto);
		return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	// 특정 메뉴 타입에 해당하는 메뉴와 이미지 정보를 가져오는 엔드포인트
	@GetMapping("/{menuTypeNo}/detail")
	public ResponseEntity<List<MenuWithImagesVO>> getMenuWithImagesByType(@PathVariable int menuTypeNo) {
		try {
			List<MenuWithImagesVO> menuList = menuDao.selectList(menuTypeNo);
			if (menuList != null && !menuList.isEmpty()) {
				return ResponseEntity.ok(menuList);
			} 
			else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			log.error("Error getting menus with images by type", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
