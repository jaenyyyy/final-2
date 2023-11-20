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
		
		//바디 설정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", kakaoPayProperties.getCid());
		body.add("partner_order_id", request.getPartnerOrderId());
		body.add("partner_user_id", request.getPartnerUserId());
		body.add("item_name", request.getItemName());
		body.add("quantity", "1");
		body.add("total_amount", String.valueOf(request.getItemPrice()));
		body.add("tax_free_amount", "0");
		
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
		
		log.debug("결제 승인 완료 = {}", response.getTid());
		
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
//	public KakaoPayReadyRequestVO convert(PurchaseListVO listVO) {
//		List<PurchaseVO> list = listVO.getProduct();  //구매목록 추출
//		
//		String name = null;  //상품명 구하는거
//		int total = 0;  //일단 합계를 0원으로 설정
//		
//		//구매목록을 모두 조사하여 상품정보 추출 후 필요한 항목을 계산
//		for(PurchaseVO vo : list) {  //vo를 꺼내와서 리스트로 받아라  
//			//vo 안에는 (productNo)와 구매수량 (qty)가 있음
//			ProductDto dto = productDao.selectOne(vo.getProductNo());
//			if(name == null) { //이름이 없을떄만 이름을 넣어라 (최초 이름만 들어감)
//				name = dto.getProductName();
//			}
//			total += dto.getProductPrice() * vo.getQty();  //상품가격과 수량을 곱해서 합산해라
//		}
//		
//		//구매 수량이 2개 이상이라면 이름에 외 ?개를 추가
//		if(list.size() >=2 ) {
//			name += " 외 "+ (list.size()-1) + "건";
//		}
//		
//		//partner_order_id에 결제번호를 집어넣으면 충돌도 없고 좋지 않을까?
//		int paymentNo = paymentDao.sequence();
//		return KakaoPayReadyRequestVO.builder()
////									.partnerOrderId(UUID.randomUUID().toString())
//									.partnerOrderId(String.valueOf(paymentNo))
//									.itemName(name)
//									.itemPrice(total)
//									.build();
//	}
	
}