package com.kh.matdori.dao;

import java.util.List;
import java.util.Map;

import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.BusinessJudgeDto;
import com.kh.matdori.dto.BusinessJudgeListDto;
import com.kh.matdori.vo.BusPaginationVO;
import com.kh.matdori.vo.PaginationVO;

public interface BusinessJudgeDao {
	/* void createBusinessJudge(BusinessJudgeDto businessJudgeDto); */

	 void updateBusinessJudge(BusinessJudgeDto judgeDto);

	List<BusinessJudgeListDto> getList(BusPaginationVO vo);
	
	int countList(BusPaginationVO vo); //검색 + 페이지네이션
	
}
