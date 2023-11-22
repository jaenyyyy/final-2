package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.PaymentDto;

@Repository
public class PaymentDaoImpl implements PaymentDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("payment.sequence");
	}

	@Override
	public void insert(PaymentDto paymentDto) {
		sqlSession.insert("payment.insert", paymentDto);
	}

	@Override
	public List<PaymentDto> listByCustomer(String paymentCustomer) {
		return sqlSession.selectList("payment.listByCustomer", paymentCustomer);
	}

	@Override
	public PaymentDto selectOne(int paymentNo) {
		return sqlSession.selectOne("payment.detail", paymentNo);
	}

	@Override
	public void cancelPayment(PaymentDto paymentDto) {
		sqlSession.update("payment.cancelPayment", paymentDto);
	}

}
