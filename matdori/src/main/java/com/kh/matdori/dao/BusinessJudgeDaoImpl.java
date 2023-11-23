package com.kh.matdori.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.matdori.dto.BusinessBlockDto;
import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.BusinessJudgeDto;
import com.kh.matdori.dto.BusinessJudgeListDto;
import com.kh.matdori.vo.BusPaginationVO;
import com.kh.matdori.vo.PaginationVO;

//BusinessJudgeDaoImpl.java

@Repository
public class BusinessJudgeDaoImpl implements BusinessJudgeDao {

	@Autowired
	private SqlSession sqlSession;
	

    //사업자 심사 상태 업데이드
    @Override
    public void updateBusinessJudge(BusinessJudgeDto judgeDto) {
    	Map<String, Object> params = new HashMap<>();
        params.put("judgeComment", judgeDto.getBusJudgeComment());
        params.put("judgeStatus", judgeDto.getBusJudgeStatus());
        params.put("busId", judgeDto.getBusId());
        
        //System.out.print("코맨트"+judgeDto.getBusJudgeComment());
        //System.out.print("상태"+judgeDto.getBusJudgeStatus());
        
        // 만약 해당 ID에 대한 레코드가 없다면, 삽입 작업을 수행할 수 있도록 DAO 메서드를 수정
        int count = sqlSession.selectOne("admin.checkIfBusJudgeExists", judgeDto.getBusId());
        if (count == 0) {
            sqlSession.insert("admin.insertBusJudge", params);
        } else {
        	sqlSession.update("admin.updateJudge", params);
        }
    }
















}
