//package com.kh.matdori.restcontroller;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.io.FileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.ContentDisposition;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.kh.matdori.dao.AttachDao;
//import com.kh.matdori.dao.CustomerDao;
//import com.kh.matdori.dao.ReviewDao;
//import com.kh.matdori.dto.AttachDto;
//import com.kh.matdori.dto.RestaurantDto;
//import com.kh.matdori.dto.ReviewDto;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/rest/review")
//public class ReviewRestController {
//	
//	@Autowired
//	private ReviewDao reviewDao;
//	
//	@Autowired
//	private AttachDao attachDao;
//	
//	@Autowired
//	private CustomerDao customerDao;
//
//	
//	
////	@PostMapping("/write")
////	public void insert(@ModelAttribute ReviewDto reviewDto,
////	                   @ModelAttribute RestaurantDto restaurantDto, HttpSession session,
////	                   @RequestParam MultipartFile attach) throws  IllegalStateException, IOException {
////	    String customerId = (String) session.getAttribute("name");
////	    
////	    int reviewNo = reviewDao.sequence();
////	    int resNo = restaurantDto.getResNo();
////	    
////	    reviewDto.setReviewNo(reviewNo);
////	    reviewDto.setResNo(resNo);
////	    reviewDto.setReviewWriter(customerId);
////	    
////	    
////	    reviewDao.insert(reviewDto);
////	    
////	    // 사진 등록
////	    if(!attach.isEmpty()) {
////	        int attachNo = attachDao.sequence();
////	        
////	        String home = System.getProperty("user.home");
////	        File dir = new File(home, "upload");
////	        dir.mkdirs();
////	        File target = new File(dir, String.valueOf(attachNo));
////	        attach.transferTo(target);
////	        
////	        AttachDto attachDto = new AttachDto();
////	        attachDto.setAttachNo(attachNo);
////	        attachDto.setAttachName(attach.getOriginalFilename());
////	        attachDto.setAttachSize(attach.getSize());
////	        attachDto.setAttachType(attach.getContentType());
////	        attachDao.insert(attachDto);
////	        
////	        reviewDao.connect(reviewNo, attachNo);
////	    }
////	}
////	
////	//파일 다운로드
////		@ResponseBody
////		@RequestMapping("/image")
////		public ResponseEntity<ByteArrayResource> image(@RequestParam int reviewNo) throws IOException{
////			AttachDto attachDto = reviewDao.findImage(reviewNo);
////			if(attachDto == null) {
////				return ResponseEntity.notFound().build();   //404반환
////			}
////			
////			String home = System.getProperty("user.home");
////			File dir = new File(home, "upload");
////			File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
////			
////			byte[] data = FileUtils.readFileToByteArray(target); //실제 파일정보 불러오기
////			ByteArrayResource resource = new ByteArrayResource(data);
////			
////			return ResponseEntity.ok()
////							  .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
////							  .contentLength(attachDto.getAttachSize())
////							  .header(HttpHeaders.CONTENT_TYPE, attachDto.getAttachType())
////							  .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
////									  .filename(attachDto.getAttachName(), StandardCharsets.UTF_8)
////									  .build().toString())
////							  .body(resource);
////		}
//	
//	
//}
