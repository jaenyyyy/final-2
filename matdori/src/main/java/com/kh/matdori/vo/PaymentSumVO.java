package com.kh.matdori.vo;

import com.kh.matdori.dto.ReservationListDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentSumVO {
	private ReservationListDto reservationListDto;
	
	public Float getSumTotal() {
		return reservationListDto.getMenuPrice() 
				* reservationListDto.getRezMenuQty();
	}
	
//	public Float getPaymentTotal() {
//		return getSumTotal() - 
//	}
}
