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

	// 초기 디렉터리 설정
	@Autowired
	private FileUploadProperties props;
	private File dir;

	@PostConstruct
	public void init() {
		dir = new File(props.getHome());
		dir.mkdirs();
	}

	// 사진업로드
	@PostMapping(value = "/upload/resNo/{resNo}/menuTypeNo/{menuTypeNo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void insert(@ModelAttribute MenuWithImagesVO vo, @PathVariable int resNo, @PathVariable int menuTypeNo)
			throws IllegalStateException, IOException {

		MenuDto menuDto = vo.getMenuDto();

		int menuNo = menuDao.sequence();

		menuDto.setMenuNo(menuNo);
		menuDto.setResNo(resNo);
		menuDto.setMenuTypeNo(menuTypeNo);

		menuDao.save(menuDto);

		MultipartFile menuImage = vo.getMenuImage();
		 if (menuImage != null && !menuImage.isEmpty()) {
		        int attachNo = attachDao.sequence();
		        File target = new File(dir, String.valueOf(attachNo));
		        menuImage.transferTo(target);

		        AttachDto attachDto = new AttachDto();
		        attachDto.setAttachNo(attachNo);
		        attachDto.setAttachName(menuImage.getOriginalFilename());
		        attachDto.setAttachSize(menuImage.getSize());
		        attachDto.setAttachType(menuImage.getContentType());
		        attachDao.insert(attachDto);

		        menuDao.insertMenuImage(menuNo, attachNo);
		    }
		}

	@PostMapping("/")
	public void insert(@RequestBody MenuDto menuDto) {
		menuDao.save(menuDto);
	}

	@DeleteMapping("/delete/{menuNo}")
	public ResponseEntity<String> delete(@PathVariable int menuNo) {
		AttachDto attachDto = menuDao.findMenuImage(menuNo);

		if (attachDto != null) {
			File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
			target.delete();// 실제파일 삭제
			attachDao.delete(attachDto.getAttachNo());
		}

		boolean result = menuDao.delete(menuNo);
		if (result) {
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(404).build();
		}
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
	public ResponseEntity<MenuWithImagesVO> find(@PathVariable int menuNo) {
		MenuWithImagesVO vo = menuDao.selectOne(menuNo);
		if (vo != null) {
			return ResponseEntity.ok().body(vo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/selectOneByMenuNo/{menuNo}")
	public ResponseEntity<MenuDto> selectOneByMenuNo(@PathVariable int menuNo) {
		MenuDto menuDto = menuDao.selectOneByMenuNo(menuNo);
		if (menuDto != null) {
			return ResponseEntity.ok().body(menuDto);
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

	@PutMapping(value = "/edit/{menuNo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> edit(@PathVariable int menuNo, @ModelAttribute MenuWithImagesVO vo) throws IllegalStateException, IOException {
	    MenuDto menuDto = vo.getMenuDto();

	    boolean result = menuDao.edit(menuNo, menuDto);
//	    log.debug("menuDto={}", menuDto);

	    // 메뉴 이미지 처리 ``dd
	    MultipartFile menuImage = vo.getMenuImage();
	    if (menuImage != null && !menuImage.isEmpty()) {
	       AttachDto attachDto = menuDao.findMenuImage(menuNo);
	       
	       if(attachDto != null) {
	    	   attachDao.delete(attachDto.getAttachNo());
	    	   File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
	    	   target.delete();
	       }
	    	 	
	    	
	    	int attachNo = attachDao.sequence();
	        File insertTarget = new File(dir, String.valueOf(attachNo));
	        menuImage.transferTo(insertTarget);

	        AttachDto insertAttachDto = new AttachDto();
	        insertAttachDto.setAttachNo(attachNo);
	        insertAttachDto.setAttachName(menuImage.getOriginalFilename());
	        insertAttachDto.setAttachSize(menuImage.getSize());
	        insertAttachDto.setAttachType(menuImage.getContentType());
	        attachDao.insert(insertAttachDto);

	        menuDao.insertMenuImage(menuNo, attachNo);
	    }

	    return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	// 특정 메뉴 타입에 해당하는 메뉴와 이미지 정보를 가져오는 엔드포인트
//	@GetMapping("/{menuTypeNo}/detail")
//	public ResponseEntity<List<MenuWithImagesVO>> getMenuWithImagesByType(@PathVariable int menuTypeNo) {
//		try {
//			List<MenuWithImagesVO> menuList = menuDao.selectList(menuTypeNo);
//			if (menuList != null && !menuList.isEmpty()) {
//				return ResponseEntity.ok(menuList);
//			} 
//			else {
//				return ResponseEntity.notFound().build();
//			}
//		} catch (Exception e) {
//			log.error("Error getting menus with images by type", e);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}

	@GetMapping("/list/{menuTypeNo}")
	public List<MenuDto> list(@PathVariable int menuTypeNo) {
		return menuDao.selectListByMenuTypeNo(menuTypeNo);
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
