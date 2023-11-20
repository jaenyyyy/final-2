package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.PaymentDto;

public interface PaymentDao {
	
	int sequence(); //시퀀스 등록
	void insert(PaymentDto paymentDto); //결제 등록
	List<PaymentDto> listByCustomer(String paymentCustomer); //결제 리스트 (회원별)
	PaymentDto selectOne(int paymentNo); //결제 상세
	void cancelPayment(int paymentNo); //결제 취소
	
}
