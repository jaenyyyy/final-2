package com.kh.matdori.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
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
import com.kh.matdori.dao.MenuByReservationDao;
import com.kh.matdori.dao.MenuDao;
import com.kh.matdori.dao.PaymentDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dao.SeatDao;
import com.kh.matdori.dto.ClockDto;
import com.kh.matdori.dto.MenuByReservationDto;
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
	@Autowired
	private MenuByReservationDao menuByReservationDao;
	
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
						 ,HttpSession session
						 ,@RequestParam("rezResNo") int rezResNo
						) {
		// clockList, seatList를 데이터베이스에서 조회
		RestaurantDto resDto = restaurantDao.selectOne(rezResNo);
		List<ClockDto> clockList = clockDao.clockList(rezResNo);
		List<SeatDto> seatList = seatDao.seatList(rezResNo);
		List<MenuWithImagesVO> menuList = menuDao.getMenuByRes(rezResNo);
		
		session.setAttribute("rezResNo", rezResNo);
		
	    // 모델에 clockList, seatList를 추가
		model.addAttribute("resDto", resDto);
	    model.addAttribute("clockList", clockList);
	    model.addAttribute("seatList", seatList);
	    model.addAttribute("menuList", menuList);
		return "reservation/booking";
	}
	@PostMapping("/insert")
	public String insert(HttpSession session,
						 @ModelAttribute ReservationDto reservationDto,
//						 @RequestParam("rezResNo") int rezResNo,
	                     @RequestParam("selectedClock") int clockNo,
	                     @RequestParam("selectedSeat") int seatNo,
	                     @RequestParam("selectedMenus") ArrayList<Integer> selectedMenuNos,
	                     @RequestParam("selectedQtys") ArrayList<Integer> selectedQtys) 
	{
//		log.debug("clockNo={}",clockNo);
//		log.debug("seatNo={}",seatNo);
//		log.debug("selectedMenuNos={}",selectedMenuNos);
//		log.debug("selectedQtys={}",selectedQtys);
	    
		
		int rezNo = reservationDao.sequence();
				reservationDto.setRezNo(rezNo);
//				log.debug("rezNo={}",rezNo);
		
		//회원별 예약 처리
		String rezCustomerId = (String)session.getAttribute("name");
		reservationDto.setRezCustomerId(rezCustomerId);
		
//		int rezResNo = (int) session.getAttribute("rezResNo"); // 어떻게든 rezResNo 값을 가져와야 함
//		reservationDto.setRezResNo(rezResNo);
//		log.debug("rezResNo={}",rezResNo);
		
		
	    // 선택한 시간,좌석 값으로 시간,좌석 정보 조회
	    ClockDto selectedClock = clockDao.selectOne(clockNo);
	    SeatDto selectedSeat = seatDao.selectOne(seatNo);
//	    List<MenuWithImagesVO> selectedMenus = reservationDao.menuList(rezNo); 
//	     시간 정보를 ReservationDto에 설정
	    if (selectedClock != null && selectedSeat != null) {
	        reservationDto.setRezClockNo(selectedClock.getClockNo());
	        reservationDto.setRezSeatNo(selectedSeat.getSeatNo());
	    }
	    log.debug("reservationDto={}", reservationDto);
	    
	    reservationDao.insert(reservationDto);
	 // MenuByReservationDto를 사용하여 여러 메뉴를 데이터베이스에 등록
	    
	    List<MenuByReservationDto> menuList = new ArrayList<>();

		 // 각 메뉴에 대한 번호와 수량을 리스트로 관리
		 for (int i = 0; i < selectedMenuNos.size(); i++) {
		     int menuNo = selectedMenuNos.get(i);
		     int qty = selectedQtys.get(i);
	
		     menuList.add(MenuByReservationDto.mbrDto(rezNo, menuNo, qty));
		     // 메뉴 정보를 조회하고 MenuByReservationDto에 추가
//		     MenuWithImagesVO menuWithImagesVO = menuDao.selectOne(menuNo);
//		     if (menuWithImagesVO != null) {
//		         MenuDto menuDto = menuWithImagesVO.getMenuDto();
//	
//		         // MenuByReservationDto를 새로 생성하여 초기화
//		         MenuByReservationDto menuByReservationDto = new MenuByReservationDto();
//		         menuByReservationDto.setRezNo(rezNo);
//		         menuByReservationDto.getMenuNos().add(menuDto.getMenuNo());
//		         menuByReservationDto.getMenuQtys().add(qty);
//	
//		         // 각 메뉴 번호와 예약 번호의 조합을 데이터베이스에 등록
//		         menuByReservationDao.insert(menuByReservationDto);
//		         
//		         // 생성된 객체를 리스트에 추가
//		         menuList.add(menuByReservationDto);
//		     }
//		     log.debug("menuList={}", menuList);
		 }
		 
		 for(MenuByReservationDto mbr : menuList) {
			 menuByReservationDao.insert(mbr);
		 }
		 log.debug("menuList={}", menuList);


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
