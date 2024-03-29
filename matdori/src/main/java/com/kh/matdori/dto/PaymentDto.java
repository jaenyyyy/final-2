package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PaymentDto {
	private int paymentNo;
	private int paymentRezNo;
	private String paymentCustomer;
	private String paymentTid;
	private String paymentName;
	private int paymentPrice;
	private int paymentRemain; //잔여결제금액(취소 가능 금액)
	private Date paymentTime;
	private String paymentStatus;
	
	
	public boolean isCanceled() {  //이미 취소된 대상을 찾기 위해서 만든 가상의 메소드
		return paymentStatus.equals("예약취소");
	}
}
