package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public void insert(CustomerDto customerDto) {
		sqlSession.insert("customer.insert", customerDto);
	}

	@Override
	public boolean delete(String customerId) {
		int result = sqlSession.delete("customer.delete", customerId);
		if(result == 0) throw new NoTargetException();
		return false;
	}

	@Override
	public boolean updateCustomerInfo(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerDto selectOne(String customerId) {
		CustomerDto customerDto = sqlSession.selectOne("customer.selectById", customerId);
		if(customerDto == null) throw new NoTargetException();
		return customerDto;
	}

	@Override
	public List<CustomerDto> selectList() {
		return sqlSession.selectList("customer.selectAll");
	}

	@Override
	public void edit(String customerId, CustomerDto customerDto) {
	    Map<String, Object> param = Map.of("customerId", customerId, "customerDto", customerDto);
	    int result = sqlSession.update("customer.change", param);
	    if(result == 0) throw new NoTargetException();
	}


}
