package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.CustomerDto;

public interface CustomerDao {
	
	// 등록 
	void insert(CustomerDto customerDto);
	
	// 삭제 
	boolean delete(String customerId);
	
	// 이용자 정보 수정 
	void edit(String customerId, CustomerDto customerDto);
	//void edit(CustomerDto customerDto);
	
	// 이용자 아이디로 조회 
	CustomerDto selectOne(String CustomerId);
	
	// 이용자 정보 모두 조회 
	List<CustomerDto> selectList();
	
	// true가 반환되면 비번변경 성공 
	// false면 비번 변경 실패 
	boolean updateCustomerPw(String customerId, String changePw);
	
}
