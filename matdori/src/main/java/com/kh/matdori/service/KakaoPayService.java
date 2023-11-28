package com.kh.matdori.service;

import java.net.URISyntaxException;

import com.kh.matdori.vo.KakaoPayApproveRequestVO;
import com.kh.matdori.vo.KakaoPayApproveResponseVO;
import com.kh.matdori.vo.KakaoPayCancelRequestVO;
import com.kh.matdori.vo.KakaoPayCancelResponseVO;
import com.kh.matdori.vo.KakaoPayReadyRequestVO;
import com.kh.matdori.vo.KakaoPayReadyResponseVO;
import com.kh.matdori.vo.PaymentSumVO;

public interface KakaoPayService {
	
	//통신기능
	KakaoPayReadyResponseVO ready(KakaoPayReadyRequestVO request) throws URISyntaxException;
	KakaoPayApproveResponseVO approve(KakaoPayApproveRequestVO request) throws URISyntaxException;
	KakaoPayCancelResponseVO cancel(KakaoPayCancelRequestVO request) throws URISyntaxException;
	
	
	
	
//	KakaoPayReadyRequestVO convert(PaymentSumVO sumVO);
}