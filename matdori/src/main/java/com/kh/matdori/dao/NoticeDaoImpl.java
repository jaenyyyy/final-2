package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.NoticeDto;
import com.kh.matdori.error.NoTargetException;
import com.kh.matdori.vo.NoticePageVO;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("notice.sequence");
	}
	
	@Override
	public void insert(NoticeDto noticeDto) {
		sqlSession.insert("notice.write", noticeDto);
	}

	@Override
	public List<NoticeDto> selectList(NoticePageVO vo) {
		return sqlSession.selectList("notice.list");
	}

	@Override
	public NoticeDto selectOne(int noticeNo) {
		NoticeDto noticeDto = sqlSession.selectOne("notice.detail", noticeNo);
		if(noticeDto == null) throw new NoTargetException();
		return noticeDto;
	}

	@Override
	public boolean edit(NoticeDto noticeDto) {
		Map<String, Object> params = new HashMap <>();
		params.put("noticeNo", noticeDto.getNoticeNo());
		params.put("noticeDto", noticeDto);
		return sqlSession.update("notice.change", params) > 0;
	}

	@Override
	public boolean delete(int noticeNo) {
		return sqlSession.delete("notice.delete", noticeNo) > 0;
	}

	@Override
	public int countList(NoticePageVO vo) {
		if(vo.isSearch()) {
			return sqlSession.selectOne("notice.search");
		}
		else return sqlSession.selectOne("notice.page");
	}
	
}
