package com.kh.matdori.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.ReviewDto;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private AttachDao attachDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
//	//후기 작성  rest로 옮김
//	@GetMapping("/write")
//	public String insert(@RequestParam int resNo) {
//		return "review/write";
//	}
//	
//	
//	@PostMapping("/write")
//	public String insert(@ModelAttribute ReviewDto reviewDto,
//						@ModelAttribute RestaurantDto restaurantDto, HttpSession session,
//						@RequestParam MultipartFile attach) throws  IllegalStateException, IOException {
//		String customerId = (String) session.getAttribute("name");
//		
//		int reviewNo = reviewDao.sequence();
//		int resNo = restaurantDto.getResNo();
//		
//		reviewDto.setReviewNo(reviewNo);
//		reviewDto.setResNo(resNo);
//		reviewDto.setReviewWriter(customerId);
//		
//		reviewDao.insert(reviewDto);
//		
//		//사진 등록
//		if(!attach.isEmpty()) {
//			int attachNo = attachDao.sequence();
//			
//			String home = System.getProperty("user.home");
//			File dir = new File(home, "upload");
//			dir.mkdirs();
//			File target = new File(dir, String.valueOf(attachNo));
//			attach.transferTo(target);
//			
//			AttachDto attachDto = new AttachDto();
//			attachDto.setAttachNo(attachNo);
//			attachDto.setAttachName(attach.getOriginalFilename());
//			attachDto.setAttachSize(attach.getSize());
//			attachDto.setAttachType(attach.getContentType());
//			attachDao.insert(attachDto);
//			
//			reviewDao.connect(reviewNo, attachNo);
//		}
//		return "redirect:/customer/mypage";
//	}
	
	
	//파일 다운로드
	@ResponseBody
	@RequestMapping("/image")
	public ResponseEntity<ByteArrayResource> image(@RequestParam int reviewNo) throws IOException{
		AttachDto attachDto = reviewDao.findImage(reviewNo);
		if(attachDto == null) {
			return ResponseEntity.notFound().build();   //404반환
		}
		
		String home = System.getProperty("user.home");
		File dir = new File(home, "upload");
		File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
		
		byte[] data = FileUtils.readFileToByteArray(target); //실제 파일정보 불러오기
		ByteArrayResource resource = new ByteArrayResource(data);
		
		return ResponseEntity.ok()
						  .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
						  .contentLength(attachDto.getAttachSize())
						  .header(HttpHeaders.CONTENT_TYPE, attachDto.getAttachType())
						  .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
								  .filename(attachDto.getAttachName(), StandardCharsets.UTF_8)
								  .build().toString())
						  .body(resource);
	}
	
	
	//매장에 나오는 후기 목록  (나중에 레스토랑으로 옮길 것)
	@RequestMapping("/listByRes")
	public String list(@ModelAttribute RestaurantDto restaurantDto,
						Model model, @ModelAttribute ReviewDto reviewDto) {
		int resNo = restaurantDto.getResNo();
		reviewDto.setReviewNo(resNo);
		
		List <ReviewDto> listByRes = reviewDao.selectListByRes(resNo);
		model.addAttribute("listByRes", listByRes);
		
		return "review/list";
	}
	
	
	
//	//상세 - 상세는 모달로 구현(레스트로 옮겨야 할듯)
//	@RequestMapping("/detail") 
//	public String detail(@RequestParam int reviewNo, Model model) {
//		ReviewDto reviewDto = reviewDao.selectOne(reviewNo);
//		model.addAttribute("reiewDto", reviewDto);
//		
//		String reviewWriter = reviewDto.getReviewWriter();
//		if(reviewWriter != null) {
//			CustomerDto customerDto = customerDao.selectOne(reviewWriter);
//			model.addAttribute("reviewWriterDto", customerDto);
//		}
//		return "비동기로 쓰면 없어질 주소";
//	}
	
	
//	//수정
//	@GetMapping("/edit")
//	public String edit(@RequestParam int reviewNo, Model model,
//					@ModelAttribute RestaurantDto restaurantDto) {
//		
//		int resNo = restaurantDto.getResNo();
//		ReviewDto reviewDto = reviewDao.selectOne(reviewNo);
//		
//		reviewDto.setResNo(resNo);
//		
//		model.addAttribute("reviewDto", reviewDto);
//		return "이거도 비동기라 없어질 주소";
//	}
//	
//	
//	@PostMapping("/edit")
//	public String edit(@ModelAttribute ReviewDto reviewDto,
//						@RequestParam MultipartFile attach,
//						@ModelAttribute RestaurantDto restaurantDto) throws IllegalStateException, IOException{
//		reviewDao.update(reviewDto);
//		int resNo = restaurantDto.getResNo();
//		reviewDto.setResNo(resNo);
//		
//		if(!attach.isEmpty()) { //파일이 있으면
//			//파익삭제
//			AttachDto attachDto = reviewDao.findImage(reviewDto.getReviewNo());
//			String home = System.getProperty("user.home");
//			File dir = new File(home, "fado");
//			
//			if(attachDto != null) {
//				attachDao.delete(attachDto.getAttachNo());
//				File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
//				target.delete();
//			}
//			
//			//파일 추가 및 연결
//			int attachNo = attachDao.sequence();
//			
//			File insertTarget = new File(dir, String.valueOf(attachNo));
//			attach.transferTo(insertTarget);
//			
//			AttachDto insertDto = new AttachDto();
//			insertDto.setAttachNo(attachNo);
//			insertDto.setAttachName(attach.getOriginalFilename());
//			insertDto.setAttachSize(attach.getSize());
//			insertDto.setAttachType(attach.getContentType());
//			attachDao.insert(insertDto);
//			reviewDao.connect(reviewDto.getReviewNo(), attachNo);
//		}
//		return "비동기라 없어질 주소";
//	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int reviewNo,
						@ModelAttribute RestaurantDto restaurantDto,
						@ModelAttribute ReviewDto reviewDto) {
		int resNo = restaurantDto.getResNo();
		reviewDto.setResNo(resNo);
		
		AttachDto attachDto = reviewDao.findImage(reviewNo);
		reviewDao.delete(reviewNo);
		
		if(attachDto != null) {
			String home = System.getProperty("user.home");
			File dir = new File(home, "fado");
			File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
			target.delete(); //실제 파일 삭제
		}
		attachDao.delete(attachDto.getAttachNo()); //파일정보 삭제
		
		return "어디로 보낼까용";
	}
}
