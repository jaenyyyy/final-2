package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.CertDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.error.NoTargetException;


@Repository
public class CertDaoImpl implements CertDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
    @Override
    public void insert(CertDto certDto) {
    	sqlSession.insert("cert.insert", certDto);
    }


	
	@Override
	public boolean delete(String certEmail) {
		return sqlSession.delete("cert.delete", certEmail) > 0;
	}
	
	

    
    @Override
    public CertDto selectOne(String certEmail) {
    	CertDto certDto = sqlSession.selectOne("cert.selectOne", certEmail);
		if(certDto == null) throw new NoTargetException();
		return certDto;
	}

    @Override
    public CertDto selectOneIn10min(String certEmail) {
    	 return sqlSession.selectOne("cert.selectOneIn10min", certEmail);
    }

    
    @Override
    public boolean deleteOver10min(String certEmail) {
    	int result = sqlSession.delete("cert.deleteOver10min", certEmail);
		if(result == 0) throw new NoTargetException();
		return false;
	}



    @Override
    public boolean resetPassword(String customerEmail, String newPassword) {
        try {
            int result = sqlSession.update("customer.resetPassword", 
                                          Map.of("customerEmail", customerEmail, "newPassword", newPassword));
            return result > 0; // 비밀번호 업데이트가 성공하면 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 예외 발생 시 실패로 간주하고 false 반환
        }
    }
}
