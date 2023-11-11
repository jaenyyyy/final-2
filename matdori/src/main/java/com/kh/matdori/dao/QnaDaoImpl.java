package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.QnaDto;
import com.kh.matdori.dto.QnaDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.PaginationVO;

@Repository
public class QnaDaoImpl implements QnaDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("qna.sequence");
	}
	
	@Override
	public void insert(QnaDto qnaDto) {
		sqlSession.insert("qna.write", qnaDto);
	}

	@Override
	public List<QnaDto> selectList(PaginationVO vo) {
		List<QnaDto> list = sqlSession.selectList("qna.list", vo);
		return list;
	}

	@Override
	public QnaDto selectOne(int qnaNo) {
		QnaDto qnaDto = sqlSession.selectOne("qna.detail", qnaNo);
		if(qnaDto == null) throw new NoTargetException();
		return qnaDto;
	}

	@Override
	public boolean edit(QnaDto qnaDto) {
		Map<String, Object> params = new HashMap <>();
		params.put("noticeNo", qnaDto.getQnaNo());
		params.put("noticeDto", qnaDto);
		return sqlSession.update("qna.change", params) > 0;
	}

	@Override
	public boolean delete(int qnaNo) {
		return sqlSession.delete("qna.delete", qnaNo) > 0;
	}
	

	@Override
	public int countList(PaginationVO vo) {
		return sqlSession.selectOne("qna.count", vo);
	}

}
