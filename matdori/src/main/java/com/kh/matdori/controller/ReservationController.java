package com.kh.matdori.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.ClockDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.PaymentDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.SeatDao;
import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.dto.PaymentDto;
import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.SeatDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.service.KakaoPayService;
import com.kh.matdori.vo.KakaoPayApproveRequestVO;
import com.kh.matdori.vo.KakaoPayApproveResponseVO;
import com.kh.matdori.vo.KakaoPayCancelRequestVO;
import com.kh.matdori.vo.KakaoPayCancelResponseVO;
import com.kh.matdori.vo.MenuWithImagesVO;
import com.kh.matdori.vo.PaymentSumVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ClockDao clockDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private KakaoPayService kakaoPayService;
	
//	    List<PurchaseVO> list = listVO.getProduct();
//		for(PurchaseVO vo : list) {
//			ProductDto productDto = productDao.selectOne(vo.getProductNo());//상품정보 조회
//			paymentDao.insertDetail(PaymentDetailDto.builder()
//							.paymentDetailOrigin(paymentNo)//상위결제번호
//							.paymentDetailProduct(vo.getProductNo())//상품번호(vo, productDto)
//							.paymentDetailProductName(productDto.getProductName())//상품명(productDto)
//							.paymentDetailProductPrice(productDto.getProductPrice())//상품가격(productDto)
//							.paymentDetailProductQty(vo.getQty())//구매수량(vo)
//						.build());
//		}
	
	@GetMapping("/insert")
	public String insert(Model model
						 ,@RequestParam("rezResNo") int rezResNo
						) {
		// clockList, seatList를 데이터베이스에서 조회
		RestaurantDto resDto = restaurantDao.selectOne(rezResNo);
		List<ClockDto> clockList = clockDao.clockList(rezResNo);
		List<SeatDto> seatList = seatDao.seatList(rezResNo);
		
	    // 모델에 clockList, seatList를 추가
		model.addAttribute("resDto", resDto);
	    model.addAttribute("clockList", clockList);
	    model.addAttribute("seatList", seatList);
		return "reservation/booking";
	}
	@PostMapping("/insert")
	public String insert(HttpSession session,
						 @ModelAttribute ReservationDto reservationDto,
//						 @RequestParam("rezResNo") int rezResNo,
	                     @RequestParam("selectedClock") int clockNo,
	                     @RequestParam("selectedSeat") int seatNo) {
		int rezNo = reservationDao.sequence();
		
		reservationDto.setRezNo(rezNo);
		log.debug("rezNo={}",rezNo);
		
		//회원별 예약 처리
		String rezCustomerId = (String)session.getAttribute("name");
		reservationDto.setRezCustomerId(rezCustomerId);
		//매장별 처리
//		reservationDto.setRezResNo(rezResNo);
		
	    // 선택한 시간,좌석 값으로 시간,좌석 정보 조회
	    ClockDto selectedClock = clockDao.selectOne(clockNo);
	    SeatDto selectedSeat = seatDao.selectOne(seatNo);
	    // 시간 정보를 ReservationDto에 설정
	    if (selectedClock != null && selectedSeat != null) {
	        reservationDto.setRezClockNo(selectedClock.getClockNo());
	        reservationDto.setRezSeatNo(selectedSeat.getSeatNo());
	    }

	    reservationDao.insert(reservationDto);
//	    log.debug("dto={}",reservationDto);
	    return "redirect:detail?rezNo="+reservationDto.getRezNo();
	}
	
	@RequestMapping("/detail")
	public String detail(
//			HttpSession session,
						  @RequestParam int rezNo,
						 Model model) {
		
		ReservationDto rezDto = reservationDao.selectOne(rezNo);
		
//		int rezNo = vo.getReservationDto().getRezNo();  //rezNo = 예약 번호인데, 이걸 listDto에서 넘버 꺼내와서 담아옴
//		//계산을 위한 vo 생성
//		PaymentSumVO vo = new PaymentSumVO();
//		
//		//rezDto 설정을 vo 가져오는걸로 설정
//		vo.setReservationDto(rezDto);
//		
//		Float sumTotal = vo.getSumTotal();
//		Float paymentTotal = vo.getPaymentTotal();
//		int inputPoint = vo.getInputPoint();
		
		
		
		model.addAttribute("rezDto", rezDto);
//		model.addAttribute("sumTotal", sumTotal);
//	    model.addAttribute("paymentTotal", paymentTotal);
//	    model.addAttribute("inputPoint", inputPoint);
//	    
	    log.debug("rezNo={}", rezNo);
		
		return "reservation/rezDetail";
	}
	
	
	
	
	
	
	
	
	
	//결제  --재은 구역--
	@GetMapping("/payment/success")
	public String paymentSuccess(HttpSession session,
								@RequestParam String pg_token) throws URISyntaxException {
		KakaoPayApproveRequestVO request = (KakaoPayApproveRequestVO)session.getAttribute("approve");
		
		session.removeAttribute("approve");
		
		request.setPgToken(pg_token);  //토큰설정
		KakaoPayApproveResponseVO response = kakaoPayService.approve(request); //승인요청
		
		//[1] 결제번호 생성
		int paymentNo = Integer.parseInt(response.getPartnerOrderId());
		
		//[2] 결제정보 등록
		paymentDao.insert(PaymentDto.builder()
				.paymentNo(paymentNo)
				.paymentCustomer(response.getPartnerUserId())
				.paymentTid(response.getTid())
				.paymentName(response.getItemName())
				.paymentPrice(response.getAmount().getTotal())
				.paymentRemain(response.getAmount().getTotal())
				.build());
		
		
		return "redirect:/successResult";
	}
	
	@RequestMapping("/payment/successResult")
	public String paymentSuccessResult() {
		return "reservation/successResult";
	}
	
//	@RequestMapping("/payment/list")  //사용자가 보는 구매목록
	public String paymentList(Model model, HttpSession session) {
		
		String customerId = (String) session.getAttribute("name");
		model.addAttribute("list", paymentDao.listByCustomer(customerId));
		return "reservation/listByCustomer";
	}
	
	
	
	@RequestMapping("payment/cancel")
	public String paymentCancel(@RequestParam int paymentNo) throws URISyntaxException {
		//1
		PaymentDto paymentDto = paymentDao.selectOne(paymentNo);
		if(paymentDto == null || paymentDto.getPaymentRemain() == 0) {
			throw new NoTargetException("이미 취소된 결제입니다");
		}
		
		//2
		KakaoPayCancelRequestVO request = KakaoPayCancelRequestVO.builder()
					.tid(paymentDto.getPaymentTid()) //거래번호
					.cancelAmount(paymentDto.getPaymentRemain()) //잔여금액
					.build();
		KakaoPayCancelResponseVO response = kakaoPayService.cancel(request);
		
		//3
		paymentDao.cancelPayment(PaymentDto.builder()
				.paymentNo(paymentNo).paymentRemain(0)
				.build());
		
		
		return "redirect:listByCustomer";
	}
	
	
	@RequestMapping("/payment/delete")
	public String test3cancel(HttpSession session) {
		session.removeAttribute("approve");
		session.removeAttribute("listVO");
		
		return "paymentDelete";
	}
	
	@RequestMapping("/payment/fail")
	public String test3fail(HttpSession session) {
		session.removeAttribute("approve");
		session.removeAttribute("listVO");
		
		return "paymentFail";
	}
	
}
