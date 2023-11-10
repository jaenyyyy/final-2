package com.kh.matdori.dao;

import java.util.List;

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
    	int result = sqlSession.delete("cert.delete", certEmail);
		if(result == 0) throw new NoTargetException();
		return false;
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
}
