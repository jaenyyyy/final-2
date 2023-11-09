package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.NoticeDto;
import com.kh.matdori.error.NoTargetException;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(NoticeDto noticeDto) {
		sqlSession.insert("notice.write", noticeDto);
	}

	@Override
	public List<NoticeDto> selectList() {
		return sqlSession.selectList("notice.list");
	}

	@Override
	public NoticeDto selectOne(int noticeNo) {
		NoticeDto noticeDto = sqlSession.selectOne("notice.detail", noticeNo);
		if(noticeDto == null) throw new NoTargetException();
		return noticeDto;
	}

	@Override
	public void edit(int noticeNo, NoticeDto noticeDto) {
		Map<String, Object> param = Map.of("noticeNo", noticeNo, "noticeDto", noticeDto);
		int result = sqlSession.update("notice.change", param);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public void delete(int noticeNo) {
		int result = sqlSession.delete("notice.delete", noticeNo);
		if(result == 0) throw new NoTargetException();
	}

	@Override
	public List<NoticeDto> searchList(String noticeTitle) {
		return sqlSession.selectList("notice.search", noticeTitle);
	}

	@Override
	public List<NoticeDto> selectListByPage(int page, int size) {
		int end = page * size;
		int begin = end - (size - 1);
		Map params = Map.of("begin", begin, "end", end);
		return sqlSession.selectList("notice.page", params);
	}

}
