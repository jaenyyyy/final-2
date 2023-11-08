package com.kh.matdori.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class CustomerBlockDto {
	
	private String customerId;
	private Date customerBlockTime;
	private String customerBlockComment;

}
