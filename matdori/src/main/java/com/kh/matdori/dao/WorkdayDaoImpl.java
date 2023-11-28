package com.kh.matdori.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.WorkdayDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class WorkdayDaoImpl implements WorkdayDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("workday.sequence");
	}

	@Override
	public void insert(WorkdayDto workdayDto) {
		sqlSession.insert("workday.insertWorkday", workdayDto);
	}

	@Override
	public void deleteWorkday(int workdayResNo) {
		int result = sqlSession.delete("workday.deleteWorkday", workdayResNo);
	}

	@Override
	public List<WorkdayDto> selectList(int workdayResNo) {
		return sqlSession.selectList("workday.findAllWorkday", workdayResNo);
	}

	@Override
	public WorkdayDto selectOne(int workdayNo) {
		return sqlSession.selectOne("workday.findWorkday",workdayNo);
	}

	@Override
	public void updateWorkday(WorkdayDto workdayDto) {
		sqlSession.update("workday.updateWorkday", workdayDto);
    }
		
	
//	@Override
//	public void insertWorkday(WorkdayDto workdayDto) {
//		sqlSession.insert("clock2.addWorkday",workdayDto);
//		
//	}
//
//	@Override
//	public WorkdayDto selectOne(int workdayNo) {
//	WorkdayDto workdayDto = sqlSession.selectOne("workday.findWorkday",workdayNo);
//	if(workdayDto == null)
//		throw new NoTargetException();
//	return workdayDto;
//	}
//
//	@Override
//	public List<WorkdayDto> selectAllByResNo(int workResNo) {
//		return sqlSession.selectList("workday.findAllWorkday",workResNo);
//	}
//
//	@Override
//	public void edit(WorkdayDto workdayDto) {
//		workdayDto.setWorkdayNo(workdayDto.getWorkdayNo());
//		int result = sqlSession.update("workday.editWorkday",workdayDto);
//		if(result == 0)
//			throw new NoTargetException();
//	}
//
//	@Override
//	public void delete(int workdayNo) {
//		int result = sqlSession.delete("workday.deleteWorkday", workdayNo);
//		if(result==0)
//			throw new NoTargetException();
//	}
//
}
