package com.kh.matdori.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RezDetailListDto {
	private int rezNo;
	private int paymentNo;
	private String resName;
    private Date paymentTime;
    private String customerName;
    private String customerContact;
    private String paymentStatus;
    private int paymentPrice;
}
