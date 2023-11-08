package com.kh.matdori.dao;

import com.kh.matdori.dto.BusinessDto;

public interface BusinessDao {

	void insert(BusinessDto businessDto);
	BusinessDto selectOne(String busId);

	boolean update(String busId, BusinessDto businessDto);
	void delete(String busId);
}
