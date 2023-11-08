package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.QnaDto;
import com.kh.matdori.dto.QnaDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class QnaDaoImpl implements QnaDao{

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public void insert(QnaDto qnaDto) {
		sqlSession.insert("qna.write", qnaDto);
	}

	@Override
	public List<QnaDto> selectList() {
		return sqlSession.selectList("qna.list");
	}

	@Override
	public QnaDto selectOne(int qnaNo) {
		QnaDto qnaDto = sqlSession.selectOne("qna.detail", qnaNo);
		if(qnaDto == null) throw new NoTargetException();
		return qnaDto;
	}

	@Override
	public void edit(int qnaNo, QnaDto qnaDto) {
		Map<String, Object> param = Map.of("qnaNo", qnaNo, "qnaDto", qnaDto);
		int result = sqlSession.update("qna.change", param);
		if(result == 0) throw new NoTargetException();
		sqlSession.update("qna.change", param);
	}

	@Override
	public void delete(int qnaNo) {
		int result = sqlSession.delete("qna.delete", qnaNo);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public List<QnaDto> searchList(String qnaTitle) {
		return sqlSession.selectList("qna.search", qnaTitle);
	}

	@Override
	public List<QnaDto> selectListByPage(int page, int size) {
		int end = page * size;
		int begin = end - (size - 1);
		Map params = Map.of("begin", begin, "end", end);
		return sqlSession.selectList("qna.page", params);
	}

}
