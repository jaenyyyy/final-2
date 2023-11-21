package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.CustomerAdminListDto;
import com.kh.matdori.dto.CustomerBlockDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.vo.CusAdminVO;

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
	
	
	boolean updateCustomerPw(String customerId, String changePw);

	
	// dto 포함 된 회원 정보 사용하여 로그인 수행 
	CustomerDto login(CustomerDto dto);
	
	// 비밀번호 암호화 후 DB등록 
	void secureInsert(CustomerDto dto);
	
	// 회원 정보 조회 후 결과 반환 
	CustomerDto secureSelectOne(String customerId);

	CustomerDto selectOneByEmail(String customerEmail);
	
	boolean updateTemporaryPassword(String customerId, String temporaryPassword);
	
	
	// 회원 차단 기능 
	void insertBlock(String customerId);
	
	void insertCusBlock(CustomerBlockDto customerBlockDto);
	
	boolean deleteBlock(String customerId);
	
	CustomerBlockDto selectBlockOne(String customerId);
	
	CustomerBlockDto selectOneByCustomerName(String customerName);

	CustomerAdminListDto cusAdminOne(String customerId);

	int countList(CusAdminVO vo);

	List<CustomerAdminListDto> cusAdminList(CusAdminVO vo);
	
	// 회원 레벨 업데이트 
	boolean updateCustomerLevel(String customerId, String customerLevel);

	boolean updateBlock(String customerStatus);
	
	boolean updatePoint(String customerId, int customerPoint);


}
	

