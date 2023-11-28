package com.kh.matdori;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CustomerJoinTest {

	@Autowired
	private CustomerDao dao;
	
	@Test
	public void test() {
		dao.insert(CustomerDto.builder()
				.customerId("hello1234")
				.customerPw("password1234")
				.build());
	}
}
