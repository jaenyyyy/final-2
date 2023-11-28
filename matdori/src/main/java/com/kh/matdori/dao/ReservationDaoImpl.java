package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.ReservationDto;
import com.kh.matdori.dto.ReservationListDto;
import com.kh.matdori.vo.CusPaginationVO;
import com.kh.matdori.vo.MenuWithImagesVO;

import com.kh.matdori.dto.ReviewDto;

@Repository
public class ReservationDaoImpl implements ReservationDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("reservation.sequence");
	}

	// 예약등록
	@Override
	public void insert(ReservationDto reservationDto) {
		sqlSession.insert("reservation.booking", reservationDto);
	}

	@Override
	public ReservationDto selectOne(int rezNo) {
		return sqlSession.selectOne("reservation.detail", rezNo);
	}

	@Override
	public boolean isInReservation(int rezResNo, int rezClockNo, int rezSeatNo) {
		// 예약이 이미 돼있는지를 판단할 때 필요한 정보를 전달
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("rezResNo", rezResNo);
		parameters.put("rezClockNo", rezClockNo);
		parameters.put("rezSeatNo", rezSeatNo);

		// 예약이 안되어 있다면 null을 반환
		return sqlSession.selectOne("reservation.isInReservation", parameters) != null;
	}
	 
	//회원별 예약내역 조회
	@Override
	public List<ReservationListDto> rezList(CusPaginationVO vo) {
	    return sqlSession.selectList("reservation.rezList", vo);
	}
//	@Override
//	public List<ReservationListDto> rezList(CusPaginationVO vo) {
//	    vo.calculatePageInfo(); // 페이지 정보 계산
//
//	    int startRow = (vo.getPage() - 1) * vo.getSize() + 1;
//	    int endRow = startRow + vo.getSize() - 1;
//
//	    Map<String, Object> params = new HashMap<>();
//	    params.put("rezCustomerId", vo.getRezCustomerId());
//	    params.put("startRow", startRow);
//	    params.put("endRow", endRow);
//
//	    return sqlSession.selectList("reservation.rezList", params);
//	}

	
	//페이지네이션용 카운트?
	@Override
	public int rezCount(CusPaginationVO vo) {
	    return sqlSession.selectOne("reservation.rezCount", vo);
	}
	



	// 다수의 메뉴
	@Override
	public List<MenuWithImagesVO> menuList(int rezNo) {
		return sqlSession.selectList("reservation.menuListByRez", rezNo);
	}



}
