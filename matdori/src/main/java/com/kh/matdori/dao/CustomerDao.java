package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.CustomerDto;

public interface CustomerDao {
	void insert(CustomerDto customerDto);
//	boolean delete(String customerId);
	boolean updateCustomerInfo(CustomerDto customerDto);
	CustomerDto selectOne(String CustomerId);
	List<CustomerDto> selectList();
}
