
package com.kh.matdori.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.matdori.dao.AttachDao;
import com.kh.matdori.dao.CertDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.PickDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.AttachDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.NoticeDto;
import com.kh.matdori.dto.ReservationListDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.service.EmailService;
import com.kh.matdori.vo.CusPaginationVO;
import com.kh.matdori.vo.PaginationVO;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private CertDao certDao;

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private AttachDao attachDao;

	@Autowired
	private PickDao pickDao;

	// 회원가입 ok
	@GetMapping("/join")
	public String join() {
		return "customer/join";
	}

	@PostMapping("/join")
	public String join(@ModelAttribute CustomerDto customerDto) throws MessagingException, IOException {
		customerDao.insert(customerDto);
		emailService.sendCelebration(customerDto.getCustomerEmail());
		return "redirect:joinFinish";
	}

	// 회원가입 완료 ok
	@GetMapping("/joinFinish")
	public String joinFinish() {
		return "customer/joinFinish";
	}

	// 로그인 ok
	@GetMapping("/login")
	public String login() {
		return "customer/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute CustomerDto dto, HttpSession session) {

		CustomerDto target = customerDao.login(dto);
		if (target == null) {
			return "redirect:login?error";
		} else {
			// 세션 정보 설정...후 메인페이지 혹은 기존페이지로 이동
			session.setAttribute("name", target.getCustomerId());
			session.setAttribute("level", target.getCustomerLevel());
			return "redirect:/";
		}
	}

	// 로그아웃 ok
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("level");
		return "redirect:/";
	}

	// 마이페이지 ok
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		model.addAttribute("customerDto", customerDto);

		return "customer/mypage";
	}

	// 개인정보 변경 ok
	@GetMapping("/change")
	public String change(HttpSession session, Model model) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		model.addAttribute("customerDto", customerDto);
		return "customer/change";
	}

	@PostMapping("/change")
	public String change(@ModelAttribute CustomerDto inputDto, HttpSession session) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto findDto = customerDao.selectOne(customerId);

		// 사용자가 입력한 비밀번호를 암호화
		String encryptedInputPassword = encoder.encode(inputDto.getCustomerPw());

		// 암호화된 입력 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
		if (encoder.matches(inputDto.getCustomerPw(), findDto.getCustomerPw())) {
			// 암호화된 비밀번호를 DTO에 설정
			inputDto.setCustomerPw(encryptedInputPassword);

			inputDto.setCustomerId(customerId);
			customerDao.edit(customerId, inputDto);

			return "customer/changeFinish";

		} else {
			return "redirect:change?error";
		}
	}


	

	// 비밀번호 찾기 ?
	@GetMapping("/findPw")
	public String findPw() {
		return "customer/findPw";
	}

	@PostMapping("/findPw")
	public String findPw(@ModelAttribute CustomerDto customerDto) {
		// [1] 아이디로 모든 정보를 불러오고
		CustomerDto findDto = customerDao.selectOne(customerDto.getCustomerId());
		// [2] 이메일이 일치하는지 확인한다
		boolean isValid = findDto != null && findDto.getCustomerEmail().equals(customerDto.getCustomerId());
		if (isValid) {// 이메일이 같다면
			// 이메일 발송 코드
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(findDto.getCustomerEmail());
			message.setSubject("비밀번호 찾기 결과");
			message.setText(findDto.getCustomerPw());
			sender.send(message);

			return "redirect:changePw";
		} else {// 이메일이 다르다면
			return "redirect:findPw?error";
		}

	}

	// 비밀번호 재설정 ok
	@GetMapping("/changePw")
	public String changePw() {
		return "customer/changePw";
	}

	@PostMapping("/changePw")
	public String changePw(HttpSession session, @RequestParam String originPw, @RequestParam String changePw,
			Model model) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto findDto = customerDao.selectOne(customerId);

		// 암호화된 입력 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
		if (encoder.matches(originPw, findDto.getCustomerPw())) {
			// 새로운 비밀번호를 암호화
			String encryptedNewPassword = encoder.encode(changePw);

			// 암호화된 비밀번호를 DTO에 설정
			findDto.setCustomerPw(encryptedNewPassword);

			// customerDao.edit 메소드가 새로운 비밀번호를 업데이트할 수 있도록 수정 필요
			customerDao.edit(customerId, findDto);

			// 비밀번호 변경 완료 후 세션 무효화 및 로그아웃
			session.invalidate();

			return "redirect:changePwFinish";
		} else {
			model.addAttribute("error", "비밀번호 변경에 실패했습니다. 입력한 비밀번호를 확인하세요.");
			return "redirect:changePw?error";
		}
	}

	// 비밀번호 변경 ok
	@RequestMapping("/changePwFinish")
	public String changePwFinish() {
		return "customer/changePwFinish";
	}

	// 회원 탈퇴 ok
	@GetMapping("/exit")
	public String exit() {
		return "customer/exit";
	}

	@PostMapping("/exit")
	public String exit(HttpSession session, @RequestParam String customerPw) {
		String customerId = (String) session.getAttribute("name");
		CustomerDto customerDto = customerDao.selectOne(customerId);
		// 사용자가 입력한 비밀번호를 암호화
		String encryptedInputPassword = encoder.encode(customerPw);

		if (encoder.matches(customerPw, customerDto.getCustomerPw())) {
			// 삭제할 때도 암호화된 비밀번호를 사용
			customerDao.delete(customerId);
			session.removeAttribute("name");
			return "redirect:exitFinish";
		} else {
			return "redirect:exit?error";
		}
	}

	@RequestMapping("/exitFinish")
	public String exitFinish() {
		return "customer/exitFinish";
	}

	// 마이페이지에서 보는 예약 내역
	@RequestMapping("/rezList")
	public String list(Model model, HttpSession session, CusPaginationVO vo) {
	    String customerId = (String) session.getAttribute("name");
	    
	    vo.setRezCustomerId(customerId);

	    // 예약 리스트 조회
	    List<ReservationListDto> rezList = reservationDao.rezList(vo);
	    
	    // 예약 카운트 조회
	    int count = reservationDao.rezCount(vo);

	    System.out.print(count);
	    
	    vo.setCount(count);
	    vo.calculatePageInfo();

	    model.addAttribute("vo", vo);
	    model.addAttribute("rezList", rezList);

	    return "customer/rezList";
	}


	// 마이페이지에서 보는 찜 내역
	@RequestMapping("/pick")
	public String pickList(Model model, HttpSession session) {
		String customerId = (String) session.getAttribute("name");

		// userId를 이용하여 해당 사용자가 찜한 식당 목록 조회 (예시: pickList를 DB에서 가져오는 메서드)
		List<RestaurantDto> pickList = pickDao.pickList(customerId);

		// JSP로 찜한 식당 목록 전달
		model.addAttribute("pickList", pickList);

		return "customer/pickList";
	}

	// 리뷰 목록 가져오기
		@RequestMapping("/reviewListByCus")
		public String reviewList(Model model, HttpSession session) {
		    String customerId = (String) session.getAttribute("name");

		    List<ReviewDto> reviewList = reviewDao.selectListByCus(customerId);

		    model.addAttribute("reviewList", reviewList);

		    return "review/reviewListByCus";
		}


		@GetMapping("/reviewWrite")
		public String reviewWrite() {
			return "/review/reviewWrite";
		}

		@PostMapping("/reviewWrite")
		public String insertReview(@ModelAttribute ReviewDto reviewDto, @ModelAttribute RestaurantDto restaurantDto,
				HttpSession session, @RequestParam MultipartFile attach,
				@RequestParam(required = false) Integer reviewNo) throws IllegalStateException, IOException {
			String customerId = (String) session.getAttribute("name");

			int resNo = restaurantDto.getResNo();
			
				int ReviewNo2 = reviewDao.sequence();
				reviewDto.setReviewNo(ReviewNo2);
			
			reviewDto.setResNo(resNo);
			reviewDto.setReviewWriter(customerId);	

			reviewDao.insert(reviewDto);

			if (!attach.isEmpty()) {
				int attachNo = attachDao.sequence();

				String home = System.getProperty("user.home");
				File dir = new File(home, "upload");
				dir.mkdirs();
				File target = new File(dir, String.valueOf(attachNo));
				attach.transferTo(target);

				AttachDto attachDto = new AttachDto();
				attachDto.setAttachNo(attachNo);
				attachDto.setAttachName(attach.getOriginalFilename());
				attachDto.setAttachSize(attach.getSize());
				attachDto.setAttachType(attach.getContentType());	
				attachDao.insert(attachDto);
				reviewDao.connect(ReviewNo2, attachNo);	
				//System.out.println("첨부파일번호 = "+reviewDto.getReviewAttachNo());
			}
			//System.out.println("첨부파일번호 첨부 안할시 = "+reviewDto.getReviewAttachNo());
		    // 리뷰 작성 후 리뷰 리스트 페이지로 이동
		    return "redirect:/customer/reviewListByCus";
		}
		
	    @ResponseBody
	    @RequestMapping("/image")
	    public ResponseEntity<ByteArrayResource> image(@RequestParam int reviewNo) throws IOException {
	        AttachDto attachDto = reviewDao.findImage(reviewNo);
	        if (attachDto == null) {
	            return ResponseEntity.notFound().build(); // 404 반환
	        }

	        String home = System.getProperty("user.home");
	        File dir = new File(home, "upload");
	        File target = new File(dir, String.valueOf(attachDto.getAttachNo()));

	        byte[] data = FileUtils.readFileToByteArray(target); // 실제 파일정보 불러오기
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
	    


		//삭제
		@RequestMapping("/delete")
		public String delete(@RequestParam int reviewNo, @ModelAttribute RestaurantDto restaurantDto,
				@ModelAttribute ReviewDto reviewDto) {
			int resNo = restaurantDto.getResNo();
			reviewDto.setResNo(resNo);

			AttachDto attachDto = reviewDao.findImage(reviewNo);
			reviewDao.delete(reviewNo);
			if (attachDto != null) {
				String home = System.getProperty("user.home");
				File dir = new File(home, "matdori");
				File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
				target.delete(); // 실제 파일 삭제
				attachDao.delete(attachDto.getAttachNo()); // 파일정보 삭제
			}

			return "redirect:/customer/reviewListByCus";
		}
	}