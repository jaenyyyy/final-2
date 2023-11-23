package com.kh.matdori.restcontroller;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.kh.matdori.configuration.FileUploadProperties;
import com.kh.matdori.dao.AdminDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.ResImagesDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.vo.ResImagesVO;
import com.kh.matdori.vo.RestaurantJudgeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantRestController {

	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private AdminDao adminDao;
	
	// 초기 디렉터리 설정
		@Autowired
		private FileUploadProperties props;
		private File dir;

		@PostConstruct
		public void init() {
			dir = new File(props.getHome());
			dir.mkdirs();
		}
	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody RestaurantJudgeVO vo) {
		 // 디버그 로그 출력
		log.debug("Received busId: {}", vo.getRestaurantDto().getBusId());
		//시퀀스 꺼냄
		int resNo = restaurantDao.sequence();
		int judgeNo = adminDao.sequence();
		
		 log.debug("Generated resNo: {}, judgeNo: {}", resNo, judgeNo);
		
		vo.getRestaurantDto().setResNo(resNo);
		
		//등록함
		 restaurantDao.insert(vo.getRestaurantDto());

	    // 시퀀스 값을 받아옴
	    
	    //resNo+judgeNo 설정
	    vo.getRestaurantJudgeDto().setResNo(resNo);
	    vo.getRestaurantJudgeDto().setResJudgeNo(judgeNo);
	    
	   
	    adminDao.insertResJudge(vo.getRestaurantJudgeDto());
		return ResponseEntity.created(URI.create("/restaurant/" + resNo)).build();
		}

	@DeleteMapping("/{resNo}")
	public void delete(@PathVariable int resNo) {
		restaurantDao.delete(resNo);
	}

	@PutMapping("/{resNo}")
	public void update(@RequestBody RestaurantDto restaurantDto, @PathVariable int resNo) {
		restaurantDto.setResNo(resNo); // resNo 설정 추가
		restaurantDao.edit(resNo, restaurantDto);
	}

	@GetMapping("/resNo/{resNo}")
	public RestaurantDto find(@PathVariable int resNo) {
		return restaurantDao.selectOne(resNo);
	}

	 @PostMapping("/{resNo}/images")
	    public ResponseEntity<Void> uploadImages(@PathVariable int resNo, @ModelAttribute ResImagesVO vo) {
	        restaurantDao.insertResImage(resNo, vo.getResImages());
	        return ResponseEntity.ok().build();
	    }

	    @GetMapping("/{resNo}/images")
	    public ResponseEntity<List<ResImagesDto>> getImages(@PathVariable int resNo) {
	        List<ResImagesDto> images = restaurantDao.selectResImagesByResNo(resNo);
	        return ResponseEntity.ok(images);
	    }

	    @DeleteMapping("/image/{attachNo}")
	    public ResponseEntity<Void> deleteImage(@PathVariable int attachNo) {
	        boolean isDeleted = restaurantDao.deleteResImage(attachNo);
	        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	    }
	}
