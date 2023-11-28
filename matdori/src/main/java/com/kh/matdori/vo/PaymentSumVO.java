
package com.kh.matdori.vo;

import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.MenuByReservationDto;
import com.kh.matdori.dto.MenuDto;
import com.kh.matdori.dto.ReservationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentSumVO {
	private ReservationDto reservationDto;
	private CustomerDto customerDto; 
	private MenuDto menuDto;
	private MenuByReservationDto mbrDto;
	
	private int rezNo;
	private int qty;
	private int inputPoint;
	private int paymentTotal;
}

	
//	
	//결제 금액 (합계 금액 - 포인트 사용)
//	
//	

//	//레벨 별 페이백
//	public Float getLevelByPayback() {
//		String level = customerDto.getCustomerLevel();
//		if(level.equals("맛도리수저")) {
//			return (float) (getPaymentTotal() * 0.05);
//		}
//		else if(level.equals("다이아수저")) {
//			return (float) (getPaymentTotal() * 0.04);
//		}
//		else if(level.equals("금수저")) {
//			return (float) (getPaymentTotal() * 0.03);
//		}
//		else if(level.equals("은수저")) {
//			return (float) (getPaymentTotal() * 0.02);
//		}
//		else {
//			return (float) (getPaymentTotal());
//		}
//	
//	}
//}
