package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.OkDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class OkDaoImpl implements OkDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(OkDto okDto) {
		sqlSession.insert("ok.insert", okDto);
	}

	@Override
	public void edit(int okNo, OkDto okDto) {
		Map<String, Object> param = Map.of("okNo", okNo, "okDto", okDto);
		int result = sqlSession.update("ok.edit", param);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public void delete(int okNo) {
		int result = sqlSession.delete("ok.remove", okNo);
		if(result == 0) throw new NoTargetException();
	}
	
	@Override
	public List<OkDto> selectList() {
		return sqlSession.selectList("ok.seatListByclockNo");
	}

}
