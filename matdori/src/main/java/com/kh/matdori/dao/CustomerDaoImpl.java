package com.kh.matdori.dao;

import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerDaoImpl implements CustomerDao {@Override
	public void insert(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(String customerId) {
		// TODO Auto-generated method stub
		return false;
	}

}
