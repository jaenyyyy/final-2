package com.kh.matdori.dao;

import com.kh.matdori.dto.CustomerDto;

public interface CustomerDao {
	void insert(CustomerDto customerDto);
	boolean delete(String customerId);


	
}
