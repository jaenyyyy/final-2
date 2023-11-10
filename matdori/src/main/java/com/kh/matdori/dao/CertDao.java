package com.kh.matdori.dao;

import com.kh.matdori.dto.CertDto;

public interface CertDao {
		void insert(CertDto certDto);
		boolean delete(String certEmail);
		boolean deleteOver10min(String certEmail);
		CertDto selectOne(String certEmail);
		CertDto selectOneIn10min(String certEmail);
	}



