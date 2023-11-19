package com.kh.matdori.restcontroller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.MenuImagesDao;
import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.MenuByResDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.dto.MenuImagesDto;
import com.kh.matdori.vo.MenuWithImagesVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuRestController {

	@Autowired
	private MenuDao menuDao;
	
	//업로드 작업중
//	@Autowired
//	private AttachDao attachDao;
//	
//	@Autowired
//	private MenuImagesDao menuImagesDao;
//	
//	@Value("${file.upload-dir}")
//    private String uploadDir;
//	
//	@PostMapping("/upload")
//	public ResponseEntity<?> uploadFile(
//	        @RequestParam("file") MultipartFile file,
//	        @RequestParam("menuNo") Integer menuNo) {
//
//	    if (file.isEmpty()) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 제공되지 않았습니다.");
//	    }
//
//	    try {
//	        // 파일명 정리 (공백 및 특수문자 제거)
//	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//	        // 파일 저장 경로 생성
//	        Path targetLocation = Paths.get(uploadDir).resolve(fileName);
//
//	        // 파일을 지정된 경로에 저장
//	        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//	        // 파일 메타데이터 저장을 위한 DTO 생성 및 설정
//	        AttachDto attachDto = new AttachDto();
//	        attachDto.setAttachName(fileName);
//	        attachDto.setAttachSize(file.getSize());
//	        attachDto.setAttachType(file.getContentType());
//
//	        // attach 테이블에 메타데이터 저장
//	        attachDao.insert(attachDto);
//
//	        // menu_images 테이블에 메뉴 번호와 첨부 파일 번호 연결
//	        MenuImagesDto menuImagesDto = new MenuImagesDto();
//	        menuImagesDto.setMenuNo(menuNo);
//	        menuImagesDto.setAttachNo(attachDto.getAttachNo());
//	        menuImagesDao.insert(menuImagesDto);
//
//	        return ResponseEntity.ok().body("파일 업로드 성공");
//	    } catch (IOException ex) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
//	    }
//	}
//	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
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
