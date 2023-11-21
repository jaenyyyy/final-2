package com.kh.matdori.restcontroller;



import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.configuration.FileUploadProperties;
import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dto.AttachDto;
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

	@Autowired
	private AttachDao attachDao;
	
	//초기 디렉터리 설정
	@Autowired
	private FileUploadProperties props;
	private File dir;
	
	@PostConstruct
	public void init() {
		dir = new File(props.getHome());
		dir.mkdirs();
	}
	
	
	// 사진업로드
	 @PostMapping(value = "/upload/resNo/{resNo}/menuTypeNo/{menuTypeNo}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	   public void insert(@ModelAttribute MenuWithImagesVO vo, @PathVariable int resNo, @PathVariable int menuTypeNo) throws IllegalStateException, IOException {
	      
	      MenuDto menuDto = vo.getMenuDto();
	      
	      int menuNo = menuDao.sequence();
	      
	      menuDto.setMenuNo(menuNo);
	      
	      menuDto.setResNo(resNo);
	      menuDto.setMenuTypeNo(menuTypeNo);
	      
	      menuDao.save(menuDto);
	
	      MultipartFile menuImage =vo.getMenuImage();
	      int attachNo = attachDao.sequence();
	      File target = new File(dir,String.valueOf(attachNo));
	      menuImage.transferTo(target);
	      AttachDto attachDto = new AttachDto();
	      attachDto.setAttachNo(attachNo);
	      attachDto.setAttachName(menuImage.getOriginalFilename());
	      attachDto.setAttachSize(menuImage.getSize());
	      attachDto.setAttachType(menuImage.getContentType());   
	      attachDao.insert(attachDto);
	      
	      menuDao.insertMenuImage(menuNo,attachNo);
	   }
	
	@PostMapping("/")
	public void insert(@RequestBody MenuDto menuDto) {
		menuDao.save(menuDto);
	}

	@DeleteMapping("/delete/{menuNo}")
	public ResponseEntity<Void> delete(@PathVariable int menuNo) {
	    menuDao.delete(menuNo);
	    return ResponseEntity.noContent().build();
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

//	@GetMapping("/{menuNo}")
//	public ResponseEntity<MenuByResDto> find(@PathVariable int menuNo) {
//		MenuByResDto menuByResDto = menuDao.selectOne(menuNo);
//		if (menuByResDto != null) {
//			return ResponseEntity.ok().body(menuByResDto);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

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
	
	@GetMapping("/menu/{resNo}")
	public ResponseEntity<List<MenuWithImagesVO>> getMenuByRes(@PathVariable int resNo) {
	    try {
	        List<MenuWithImagesVO> menuByResList = menuDao.getMenuByRes(resNo); // 서비스 레이어 메서드 호출
	        if (menuByResList != null && !menuByResList.isEmpty()) {
	            return ResponseEntity.ok(menuByResList);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        log.error("Error getting menus with images by resNo", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	
}
