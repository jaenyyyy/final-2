package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.CustomerDto;

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

//	@Override
//	public boolean delete(String customerId) {
//		int result = sqlSession.delete("customer.delete", customerId);
//		if(result == 0) throw new NoTargetException();
//	}

	@Override
	public boolean updateCustomerInfo(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerDto selectOne(String CustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDto> selectList() {
		return sqlSession.selectList("customer.selectAll");
	}

}
