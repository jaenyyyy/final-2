package com.kh.matdori.dao;

import com.kh.matdori.dto.BusinessDto;

public interface BusinessDao {

	void insert(BusinessDto businessDto);
	BusinessDto selectOne(String busId);

	boolean updatePw(String busId, BusinessDto businessDto);
	boolean update(String busId, BusinessDto businessDto);
	boolean delete(String busId);
}
