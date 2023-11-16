package com.kh.matdori.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CertDto {

	private String certEmail;
	private String certNumber;
	private Date certTime;
}

