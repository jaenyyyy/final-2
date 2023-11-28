package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.CustomerAdminListDto;
import com.kh.matdori.dto.CustomerBlockDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.CusAdminVO;
import com.kh.matdori.vo.CusLevelUpVO;
//import com.kh.matdori.vo.PaymentSumVO;
import com.kh.matdori.vo.CusPaginationVO;

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
		return sqlSession.delete("customer.delete", customerId) > 0;
	
	}


	@Override
	public CustomerDto selectOne(String customerId) {
		return sqlSession.selectOne("customer.detail", customerId);

	}
	
	@Override
	public CustomerDto selectTwo(String customerId) {
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

	
	
	
	@Override  //암호화 처리 후 등록
	public void secureInsert(CustomerDto dto) {
		String origin = dto.getCustomerPw();
		String encrypt = encoder.encode(origin);
		dto.setCustomerPw(encrypt);
		
		sqlSession.insert("customer.insert", dto);
	}
	
	
	@Override //암호화 된 상태로 로그인
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
	public CustomerDto secureSelectOne(String customerId) {
		CustomerDto dto = sqlSession.selectOne("customer.detail", customerId);
		return dto;
	}


	@Override
	public CustomerDto selectOneByEmail(String customerEmail) {
		CustomerDto dto = sqlSession.selectOne("customer.getCustomerByEmail", customerEmail);
		return dto;
	}

	
	
	
	
	
	
	// 회원 차단 기능 
	@Override
	public void insertBlock(String customerId) { // 차단 
		sqlSession.insert("customerBlock.insertBlock", customerId);
	}

	@Override
	public boolean deleteBlock(String customerId) { // 차단 해제 
		return sqlSession.delete("customerBlock.deleteBlock", customerId) > 0;
	}

	
	

	@Override
	public CustomerBlockDto selectBlockOne(String customerId) {
		CustomerBlockDto customerBlockDto = sqlSession.selectOne("customerBlock.cusBlockDetail", customerId);
		return customerBlockDto;
	}

	@Override
	public CustomerBlockDto selectOneByCustomerName(String customerName) {
		CustomerBlockDto customerBlockdto = sqlSession.selectOne("customerBlock.selectOneByCustomerName", customerName);
		return customerBlockdto;
	}
	
	
	


	// 목록 
	@Override
	public List<CustomerAdminListDto> cusAdminList(CusAdminVO vo) {
		List<CustomerAdminListDto> list = sqlSession.selectList("customerBlock.cusAdminList", vo);
		return list;
	}

	// 상세
	@Override
	public CustomerAdminListDto cusAdminOne(String customerId) {
		CustomerAdminListDto customerAdminListDto
		= sqlSession.selectOne("customerBlock.cusAdminDetail", customerId);
		return customerAdminListDto;
	}

	@Override
	public boolean updateBlock(String customerStatus) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void insertCusBlock(CustomerBlockDto customerBlockDto) {
		sqlSession.insert("customerBlock.blockInsert", customerBlockDto);
		
	}
	
	
	
	
//	
//	//결제에서 쓰일 포인트 차감+페이백 
//	@Override
//	public boolean minusPoint(PaymentSumVO vo) {
//		Map<String, Object> params = new HashMap<>();
//		params.put("customerId", vo.getCustomerDto().getCustomerId());
//		params.put("customerPoint", vo.getInputPoint());
//		
//		return sqlSession.update("customer.minusPoint", vo) > 0;
//	}
//	
//	
//	@Override
//	public boolean paybackPoint(PaymentSumVO vo) {
//		Map<String, Object> params = new HashMap<>();
//		params.put("customerId", vo.getCustomerDto().getCustomerId());
//		params.put("customerPoint",	vo.getLevelByPayback());
//		
//		return sqlSession.update("customer.paybackPoint", vo) > 0;
//	}



	@Override
	public boolean updateTemporaryPassword(String customerId, String temporaryPassword) {
		  Map<String, Object> param = new HashMap<>();
	        param.put("customerId", customerId);
	        param.put("temporaryPassword", temporaryPassword);

	        int affectedRows = sqlSession.update("customer.updateTemporaryPassword", param);
	        return affectedRows > 0;
	    }


	@Override
	public boolean updateCustomerLevel(String customerId, String customerLevel) {
		Map<String, Object> params = new HashMap<>();
			params.put("customerId", customerId);
			params.put("customerLevel", customerLevel);
		return sqlSession.update("customer.levelEdit", params) > 0;
	}


	@Override
	public boolean updatePoint(String customerId, int customerPoint) {
		Map<String, Object> params = new HashMap<>();
		params.put("customerId", customerId);
		params.put("customerPoint", customerPoint);
		return sqlSession.update("customer.pointEdit", params) > 0;
	}


	@Override
	 public List<CustomerAdminListDto> getList(CusAdminVO vo) {
        return sqlSession.selectList("customer.countList", vo);
	}

	// 페이지네이션 
	@Override
	public int countList(CusAdminVO vo) {
	    return sqlSession.selectOne("customer.count", vo);
	}
	

	// 리스트 구하기 
	@Override
	public List<CustomerAdminListDto> selectCustomerListByPage(CusAdminVO vo) {
		return sqlSession.selectList("customer.countList", vo);
	}

	
	
	
	//이용횟수 조회해서 레벨업 하기 위한 카운트 구문
	@Override
	public List<CusLevelUpVO> successList() {
		return sqlSession.selectList("customer.rezSuccessCount");
	}

	//찜 페이지네이션 
	@Override
	public int pickCount(CusPaginationVO vo) {
		return sqlSession.selectOne("customer.pickCount",vo);
	}
	
	
	
}