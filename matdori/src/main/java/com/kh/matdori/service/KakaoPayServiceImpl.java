package com.kh.matdori.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.matdori.configuration.KakaoPayProperties;
import com.kh.matdori.dao.PaymentDao;
import com.kh.matdori.dao.ReservationDao;
import com.kh.matdori.vo.KakaoPayApproveRequestVO;
import com.kh.matdori.vo.KakaoPayApproveResponseVO;
import com.kh.matdori.vo.KakaoPayCancelRequestVO;
import com.kh.matdori.vo.KakaoPayCancelResponseVO;
import com.kh.matdori.vo.KakaoPayReadyRequestVO;
import com.kh.matdori.vo.KakaoPayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoPayServiceImpl implements KakaoPayService{

	@Autowired
	private KakaoPayProperties kakaoPayProperties;
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Override
	public KakaoPayReadyResponseVO ready(KakaoPayReadyRequestVO request) throws URISyntaxException {
		
		//주소 설정
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
//		String approval_url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/success").toUriString();
//		String cancel_url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/cancel").toUriString();
//		String fail_url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/fail").toUriString();
		
		//바디 설정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", kakaoPayProperties.getCid());
		body.add("partner_order_id", request.getPartnerOrderId());
		body.add("partner_user_id", request.getPartnerUserId());
		body.add("item_name", request.getItemName());
		body.add("quantity", "1");
		body.add("total_amount", String.valueOf(request.getItemPrice()));
		body.add("tax_free_amount", "0");
//		body.add("approval_url", approval_url);
//		body.add("cancel_url", cancel_url);
//		body.add("fail_url", fail_url);
//		
		//현재 페이지 주소 계산
		String path = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
		body.add("approval_url", path+"/success");
		body.add("cancel_url", path+"/cancel");
		body.add("fail_url", path+"/fail");
		
		//요청 발송
		HttpEntity entity = new HttpEntity(body, headers);
		
		KakaoPayReadyResponseVO response = 
				template.postForObject(uri, entity, KakaoPayReadyResponseVO.class);
		
		return response;
	}
	
	@Override
	public KakaoPayApproveResponseVO approve(KakaoPayApproveRequestVO request) throws URISyntaxException {
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", kakaoPayProperties.getCid());
		body.add("tid", request.getTid());
		body.add("partner_order_id", request.getPartnerOrderId());
		body.add("partner_user_id", request.getPartnerUserId());
		body.add("pg_token", request.getPgToken());
		
		HttpEntity entity = new HttpEntity(body, headers);
		
		KakaoPayApproveResponseVO response = 
				template.postForObject(uri, entity, KakaoPayApproveResponseVO.class);
		
		
		return response;
	}
	
//	@Override
//	public KakaoPayDetailResponseVO detail(KakaoPayDetailRequestVO request) throws URISyntaxException {
//		URI uri = new URI("https://kapi.kakao.com/v1/payment/order");
//		
//		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//		body.add("cid", kakaoPayProperties.getCid());
//		body.add("tid", request.getTid());
//		
//		HttpEntity entity = new HttpEntity(body, headers);
//		
//		KakaoPayDetailResponseVO response = 
//				template.postForObject(uri, entity, KakaoPayDetailResponseVO.class);
//		return response;
//	}

	@Override
	public KakaoPayCancelResponseVO cancel(KakaoPayCancelRequestVO request) throws URISyntaxException {
		URI uri = new URI("https://kapi.kakao.com/v1/payment/cancel");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", kakaoPayProperties.getCid());
		body.add("tid", request.getTid());
		body.add("cancel_amount", String.valueOf(request.getCancelAmount()));
		body.add("cancel_tax_free_amount", "0");
		
		HttpEntity entity = new HttpEntity(body, headers);
		
		KakaoPayCancelResponseVO response = 
				template.postForObject(uri, entity, KakaoPayCancelResponseVO.class);
		return response;
	}
	
	
//	@Override
//	public KakaoPayReadyRequestVO convert(PaymentSumVO sumVO) {
//	    // PaymentSumVO에서 필요한 정보들을 가져와 KakaoPayReadyRequestVO로 변환하는 로직을 작성해야 합니다.
//
//	    // 예약 정보와 결제 정보를 가져와 KakaoPayReadyRequestVO에 설정하는 예시
//	    ReservationDto reservationDto = sumVO.getReservationDto();
//	    CustomerDto customerDto = sumVO.getCustomerDto();
//
//	    // 필요한 정보들을 가져와 KakaoPayReadyRequestVO에 설정
//	    String itemName = reservationDao.selectOne(resName); // 상품명 설정
//	    int itemPrice = 0; // 상품 가격 설정
//
//	    // 예약 정보, 고객 정보 등을 활용하여 itemName, itemPrice 등의 정보 설정
//
//	    int paymentNo = paymentDao.sequence(); // 결제 번호 생성
//	    return KakaoPayReadyRequestVO.builder()
//	            .partnerOrderId(String.valueOf(paymentNo))
//	            .itemName(itemName)
//	            .itemPrice(itemPrice)
//	            .build();
//	}

	
}