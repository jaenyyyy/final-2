package com.kh.matdori.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.matdori.dao.BusinessDao;
import com.kh.matdori.dao.BusinessJudgeDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.BusinessJudgeDto;
import com.kh.matdori.dto.BusinessJudgeListDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired 
	private BusinessJudgeDao businessJudgeDao;
	
	
    @GetMapping("/business/judge/list")
    public String busJudgeList(Model model) {
        List<BusinessJudgeListDto> businessJudgeList = businessJudgeDao.getAllBusinessJudge();
        model.addAttribute("businessJudgeList", businessJudgeList);
        return "/admin/business/judgeList";
    }

    @GetMapping("/business/details/{userId}")
    public String busJudgeDetails(@PathVariable String userId, Model model) {
        BusinessDto businessDto = businessDao.getBusinessDetails(userId);

        // businessDto를 모델에 추가하여 JSP에 전달
        model.addAttribute("business", businessDto);

        return "/admin/business/judgeDetail";
    }


}
