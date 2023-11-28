package com.kh.matdori.restcontroller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.matdori.configuration.FileUploadProperties;
import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.AttachDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageRestController {

	  
	   @Autowired
	   private MenuDao menuDao;
	   
	   @Autowired
	   private RestaurantDao restaurantDao;
	   
	   @Autowired
	   private AttachDao attachDao;
	   
	   //프로필 업로드 & 다운로드 기능

	         //초기 디렉터리 설정
	         @Autowired
	         private FileUploadProperties props;
	         
	         private File dir;
	         
	         @PostConstruct
	         public void init() {
	            dir = new File(props.getHome());
	            dir.mkdirs();
	         }
	         
	         
	   @GetMapping("/menu/{menuNo}")
	   public ResponseEntity<ByteArrayResource>downloadMenuImage(@PathVariable int menuNo) throws IOException{
	      log.debug("menuNo={}",menuNo);
	      
	      AttachDto attachDto = menuDao.findMenuImage(menuNo);
	      log.debug("attachDto={}",attachDto);
	      
	      if (attachDto == null) {
	          // 이미지가 존재하지 않는 경우, 적절한 HTTP 상태 코드 반환
	          return ResponseEntity.notFound().build();
	      }
	      
	      File target = new File(dir,String.valueOf(attachDto.getAttachNo()));
	      byte[] data=FileUtils.readFileToByteArray(target);//실제파일정보 불러오기
	      ByteArrayResource resource=new ByteArrayResource(data);
	      
	      return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
	            .contentLength(attachDto.getAttachSize())
	            .header(HttpHeaders.CONTENT_TYPE,attachDto.getAttachType())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .header("Content-Disposition","attachment;filename="+attachDto.getAttachName())

	            .body(resource);
	   }
	   
	   @GetMapping("/restaurant/image/{attachNo}")
	   public ResponseEntity<ByteArrayResource> getImage(@PathVariable int attachNo) throws IOException {
	       AttachDto attachDto = attachDao.selectOne(attachNo);
	       if (attachDto == null) {
	           return ResponseEntity.notFound().build();
	       }

	       File target = new File(dir, String.valueOf(attachNo));
	       byte[] data = FileUtils.readFileToByteArray(target); // 실제 파일 정보 불러오기
	       ByteArrayResource resource = new ByteArrayResource(data);

	       return ResponseEntity.ok()
	               .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
	               .contentLength(attachDto.getAttachSize())
	               .header(HttpHeaders.CONTENT_TYPE, attachDto.getAttachType())
	               .contentType(MediaType.parseMediaType(attachDto.getAttachType()))
	               .header("Content-Disposition", "inline;filename=" + URLEncoder.encode(attachDto.getAttachName(), StandardCharsets.UTF_8.name()))
	               .body(resource);
	   }

	   @GetMapping("/restaurant/image/first/{resNo}")
	   public ResponseEntity<ByteArrayResource> getFirstImage(@PathVariable int resNo) throws IOException {
	       List<Integer> attachNos = restaurantDao.findImageNoByRes(resNo); // resNo에 해당하는 모든 이미지 번호 조회
	       if (attachNos == null || attachNos.isEmpty()) {
	           return ResponseEntity.notFound().build();
	       }

	       Integer minAttachNo = Collections.min(attachNos); // 가장 낮은 attachNo 찾기
	       AttachDto attachDto = attachDao.selectOne(minAttachNo);
	       if (attachDto == null) {
	           return ResponseEntity.notFound().build();
	       }

	       File target = new File(dir, String.valueOf(minAttachNo));
	       byte[] data = FileUtils.readFileToByteArray(target); // 실제 파일 정보 불러오기
	       ByteArrayResource resource = new ByteArrayResource(data);

	       return ResponseEntity.ok()
	               .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
	               .contentLength(attachDto.getAttachSize())
	               .header(HttpHeaders.CONTENT_TYPE, attachDto.getAttachType())
	               .contentType(MediaType.parseMediaType(attachDto.getAttachType()))
	               .header("Content-Disposition", "inline;filename=" + URLEncoder.encode(attachDto.getAttachName(), StandardCharsets.UTF_8.name()))
	               .body(resource);
	   }
	   @GetMapping("/restaurant/images/{resNo}")
	   public String getImages(@PathVariable int resNo, Model model) {
	       // resNo에 해당하는 모든 이미지 번호 조회
	       List<Integer> attachNos = restaurantDao.findImageNoByRes(resNo);
	       
	       if (attachNos != null && !attachNos.isEmpty()) {
	           // 첫 번째 이미지 번호를 제외합니다.
	           attachNos.remove(0);
	           // 나머지 이미지 번호 리스트를 모델에 추가합니다.
	           model.addAttribute("imageNos", attachNos);
	       }

	       // JSP 페이지 이름을 반환합니다. 예를 들어, 'restaurantImages.jsp' 페이지가 있을 경우:
	       return "menuList";
	   }
	   
//	   @GetMapping("/movieMain/{movieNo}")
//	   public ResponseEntity<ByteArrayResource>downloadMovieMainImage(@PathVariable int movieNo) throws IOException{
//	      
//	      log.debug("movieNo={}",movieNo);
//	      
//	      ImageDto movieMainImageDto = movieDao.findMainImage(movieNo);
//	      
//	      log.debug("movieMainImageDto={}",movieMainImageDto);
//	      
//	      File target = new File(dir,String.valueOf(movieMainImageDto.getImageNo()));
//	      byte[] data=FileUtils.readFileToByteArray(target);//실제파일정보 불러오기
//	      ByteArrayResource resource=new ByteArrayResource(data);
//	      
//	      return ResponseEntity.ok()
//	            .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
//	            .contentLength(movieMainImageDto.getImageSize())
//	            .header(HttpHeaders.CONTENT_TYPE,movieMainImageDto.getImageType())
//	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
//	            .header("Content-Disposition","attachment;filename="+movieMainImageDto.getImageName())
//	            
//	            .body(resource);
//	   }
//	   
//	   @GetMapping("/{imageNo}")
//	   public ResponseEntity<ByteArrayResource>downloadImage(@PathVariable int attachNo) throws IOException{
//	      
//	      AttachDto attachDto = attachDao.selectOne(attachNo);
//	      
//	      File target = new File(dir,String.valueOf(attachNo));
//	      byte[] data=FileUtils.readFileToByteArray(target);
//	      ByteArrayResource resource=new ByteArrayResource(data);
//	      return ResponseEntity.ok()
//	            .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
//	            .contentLength(attachDto.getImageSize())
//	            .header(HttpHeaders.CONTENT_TYPE,attachDto.getImageType())
//	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
//	            .header("Content-Disposition","attachment;filename="+attachDto.getImageName())
//	            
//	            .body(resource);
//	   }
  
	}
	
	

