package com.kh.matdori.dao;

import java.util.List;

import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.BusinessJudgeDto;
import com.kh.matdori.dto.BusinessJudgeListDto;

public interface BusinessJudgeDao {
	void createBusinessJudge(BusinessJudgeDto businessJudgeDto);

	List<BusinessJudgeListDto> getAllBusinessJudge();

	
}
