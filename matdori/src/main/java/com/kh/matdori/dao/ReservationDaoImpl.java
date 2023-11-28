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
import com.kh.matdori.vo.WorkdayVO;
import com.kh.matdori.dto.ReviewDto;
import com.kh.matdori.dto.RezDetailListDto;

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
   
   //선택된 날짜
   @Override
	public WorkdayVO selectDate(String inputDate) {
		return sqlSession.selectOne("reservation.checkDate",inputDate);
	}

	@Override
	public RezDetailListDto selectDetail(int rezNo) {
		return sqlSession.selectOne("reservation.rezDetail", rezNo);
	}
	

}
