package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public void insert(CustomerDto customerDto) {
		String orgin = customerDto.getCustomerPw();
		String encrypt = encoder.encode(orgin);
		customerDto.setCustomerPw(encrypt);
		
		 // 확인을 위해 로그로 출력
	    System.out.println("Encrypted Password: " + encrypt);

	    sqlSession.insert("customer.insert", customerDto);
		
	}
	
	
	@Override
	public boolean delete(String customerId) {
		int result = sqlSession.delete("customer.delete", customerId);
		if(result == 0) throw new NoTargetException();
		return false;
	}


	@Override
	public CustomerDto selectOne(String customerId) {
		CustomerDto customerDto = sqlSession.selectOne("customer.detail", customerId);
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
	    int result = sqlSession.update("customer.edit", param);
	    if(result == 0) throw new NoTargetException();
	}
	
	
//	@Override
//	public void edit(CustomerDto customerDto) {
//		sqlSession.insert("customer.edit", customerDto);
//	}
//	

	@Override
	public boolean updateCustomerPw(String customerId, String changePw) {
	    Map<String, Object> param = Map.of("customerId", customerId, "changePw", changePw);
	    int result = sqlSession.update("customer.updatePassword", param);
	    if (result == 0) {
	        throw new NoTargetException();
	    }
	    return true;
	}

	
	
	
	
	
	@Override
	public CustomerDto login(CustomerDto dto) {
		CustomerDto target = sqlSession.selectOne("customer.detail", dto.getCustomerId());
		
		if(target != null) { // 아이디가 존재한다면
			boolean result = encoder.matches(dto.getCustomerPw(), target.getCustomerPw());
		if(result == true) { // 비밀번호가 암호화 도구에 의해 맞다고 판정되면 
			return target;
		}
	}	
		return null;
	}

	@Override
	public void secureInsert(CustomerDto dto) {
	    String origin = dto.getCustomerPw();
	    String encrypt = encoder.encode(origin);
	    dto.setCustomerPw(encrypt);

	    sqlSession.insert("customer.insert", dto);
	}

	@Override
	public CustomerDto secureSelectOne(String customerId) {
		CustomerDto dto = sqlSession.selectOne("customer.detail", customerId);
		return dto;
	}

	


}
