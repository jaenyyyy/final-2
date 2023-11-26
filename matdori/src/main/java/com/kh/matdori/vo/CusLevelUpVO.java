package com.kh.matdori.vo;

import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.PaymentDto;
import com.kh.matdori.dto.ReservationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CusLevelUpVO {
	private CustomerDto customerDto;
	private PaymentDto paymentDto;
	private ReservationDto reservationDto;
}
