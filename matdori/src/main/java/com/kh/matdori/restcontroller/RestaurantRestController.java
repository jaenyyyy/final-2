package com.kh.matdori.restcontroller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.configuration.FileUploadProperties;
import com.kh.matdori.dao.AdminDao;
import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.RestaurantDto;
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

	@Autowired
	private AttachDao attachDao;

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody RestaurantJudgeVO vo) {
		// 디버그 로그 출력
		log.debug("Received busId: {}", vo.getRestaurantDto().getBusId());
		// 시퀀스 꺼냄
		int resNo = restaurantDao.sequence();
		int judgeNo = adminDao.sequence();

		log.debug("Generated resNo: {}, judgeNo: {}", resNo, judgeNo);

		vo.getRestaurantDto().setResNo(resNo);

		// 등록함
		restaurantDao.insert(vo.getRestaurantDto());

		// 시퀀스 값을 받아옴

		// resNo+judgeNo 설정
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

	// 초기 디렉터리 설정
	@Autowired
	private FileUploadProperties props;
	private File dir;

	@PostConstruct
	public void init() {
		dir = new File(props.getHome());
		dir.mkdirs();
	}

//    // 이미지 업로드
	@PostMapping(value = "/upload/resNo/{resNo}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadImage(@PathVariable int resNo, @RequestPart List<MultipartFile> resImages) {
		if (resImages == null || resImages.isEmpty()) {
			return ResponseEntity.badRequest().body("No images provided.");
		}

		try {
			for (MultipartFile file : resImages) {
				if (!file.isEmpty()) {
					int attachNo = attachDao.sequence(); // 첨부파일 번호 생성
					File target = new File(dir, String.valueOf(attachNo));
					file.transferTo(target);

					AttachDto attachDto = new AttachDto();
					attachDto.setAttachNo(attachNo);
					attachDto.setAttachName(file.getOriginalFilename());
					attachDto.setAttachSize(file.getSize());
					attachDto.setAttachType(file.getContentType());

					log.debug("attachDto={}", attachDto);
					attachDao.insert(attachDto);

					restaurantDao.insertResImage(resNo, attachNo); // 매장 이미지 정보 저장
				}
			}
			return ResponseEntity.ok("Images uploaded successfully.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during image upload.");
		}
	}

	// 이미지 삭제
	@DeleteMapping("/deleteImage/{attachNo}")
	public ResponseEntity<?> deleteImage(@PathVariable int attachNo) {
		try {
			AttachDto attachDto = attachDao.selectOne(attachNo); // selectOne() 메서드 필요
			if (attachDto != null) {
				File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
				if (target.delete()) {
					attachDao.delete(attachNo); // delete() 메서드 구현 필요
					// 여기에 resNo와 attachNo의 연결을 끊는 로직 추가 (예: resImageDao.deleteResImage(attachNo);)
					return ResponseEntity.ok("Deleted successfully.");
				}
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed.");
		}
	}

	// resNo별 이미지 리스트 조회
	@GetMapping("/image/{resNo}")
	public ResponseEntity<?> getImagesByRes(@PathVariable int resNo) {
		try {
			List<Integer> imagesNo = restaurantDao.findImageNoByRes(resNo);
			List<AttachDto> image = new ArrayList<>();

			for (Integer imageNo : imagesNo) {
				AttachDto attachDto = attachDao.selectOne(imageNo);
				if (attachDto != null) {
					image.add(attachDto);
				}
			}

			if (!imagesNo.isEmpty()) {
				return ResponseEntity.ok( image);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No images found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving images.");
		}
	}
	
	// 공지 조회
	@GetMapping("/notice/{resNo}")
	public ResponseEntity<?> getNotice(@PathVariable int resNo) {
	    String notice = restaurantDao.findNotice(resNo);
	    
	    System.out.println("공지내용"+notice);
	    if (notice != null) {
	        return ResponseEntity.ok().body(notice); 
	        
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found for resNo: " + resNo);
	    }
	}


    // 공지 등록/수정
	@PutMapping("/notice/update/{resNo}")
	public ResponseEntity<?> updateNotice(@PathVariable int resNo, @RequestBody RestaurantDto restaurantDto) {
	    try {
	        restaurantDto.setResNo(resNo); // 업데이트할 레스토랑 번호 설정
	        restaurantDao.updateNotice(restaurantDto); // DAO에 업데이트 요청 전달
	        return ResponseEntity.ok("Notice updated successfully for resNo: " + resNo);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update notice for resNo: " + resNo);
	    }
	}


}