package com.kh.matdori.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.matdori.dao.AdminDao;
import com.kh.matdori.dao.BusinessDao;
import com.kh.matdori.dao.BusinessJudgeDao;
import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.BusinessBlockDto;
import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.BusinessJudgeDto;
import com.kh.matdori.dto.BusinessJudgeListDto;
import com.kh.matdori.dto.CustomerAdminListDto;
import com.kh.matdori.dto.CustomerBlockDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.RestaurantAdminListDto;
import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.vo.CusAdminVO;
import com.kh.matdori.vo.ResAdminVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	


	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired 
	private BusinessJudgeDao businessJudgeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
	
	@RequestMapping("/")
	public String home() {
		return "/admin/home";
	}
	
	
    @GetMapping("/business/judge/list")
    public String busJudgeList(Model model) {
        List<BusinessJudgeListDto> businessJudgeList = businessJudgeDao.getAllBusinessJudge();
        model.addAttribute("businessJudgeList", businessJudgeList);
        return "/admin/business/judgeList";
    }

    @GetMapping("/business/details/{userId}")
    public String busJudgeDetails(@PathVariable String userId, Model model) {
        BusinessDto businessDto = businessDao.getBusinessDetails(userId);
        model.addAttribute("business", businessDto);
        return "/admin/business/judgeDetail";
    }
    
    
    //사업자 등록 심사 승인,거절
    @PostMapping("/business/details/{userId}")
    public String judgeBusiness(@RequestParam String busId,
                                @RequestParam String judgeComment,
                                @RequestParam String judgeStatus) {
        BusinessJudgeDto judgeDto = new BusinessJudgeDto();
        judgeDto.setBusId(busId);
        judgeDto.setBusJudgeComment(judgeComment);
        judgeDto.setBusJudgeStatus(judgeStatus);

        // businessJudgeDao를 직접 호출하여 업데이트
        businessJudgeDao.updateBusinessJudge(judgeDto);
        System.out.println("BusJudgeComment: " + judgeDto.getBusJudgeStatus());
        return "redirect:/admin/business/judge/list";
    }
    

    
//    restController로 옮김
//    //레스토랑 차단 기능
//    @RequestMapping("/restaurant/block")
//    public String resBlock(@RequestParam int resNo) {
//    	adminDao.insertResBlock(resNo);
//    	return "redirect:/admin/restaurant/detail";
//    }
//    
//    //레스토랑 차단 해제
//    @RequestMapping("/restaurant/cancel")
//    public String resCancel(@RequestParam int resNo) {
//    	adminDao.deleteResBlock(resNo);
//    	return "redirect:/admin/restaurant/detail";
//    }
    
    
    //레스토랑 관리자 시점 (+차단 +심사 리스트)
    @RequestMapping("/restaurant/list")
    public String list(@ModelAttribute("vo") ResAdminVO vo, Model model) {
    	List <RestaurantAdminListDto> list = adminDao.resAdminList(vo);
    	model.addAttribute("list", list);
    	return "/admin/restaurant/list";
    }
    
    
    //레스토랑 관리자 시점(+차단 +심사 상세)
    @RequestMapping("/restaurant/detail")
    public String detail(@RequestParam int resNo, Model model) {
    	
    	RestaurantAdminListDto restaurantAdminListDto
    		= adminDao.resAdminOne(resNo);
    	model.addAttribute("restaurantAdminListDto", restaurantAdminListDto);
    	
    	RestaurantDto restaurantDto = restaurantDao.selectOne(resNo);
    	model.addAttribute("restaurantDto", restaurantDto);
    	
    	RestaurantBlockDto blockDto = adminDao.selectBlockOne(resNo);
    	model.addAttribute("restaurantBlockDto", blockDto);
    	
    	return "/admin/restaurant/detail";
    }
    
    
  //사업자 관리 리스트
    @GetMapping("/business/blockManager/list")
    public String businessBlockManagerList(Model model) {
        List<BusinessBlockDto> blockedBusinesses = adminDao.getAllBlockedBusinesses();
        model.addAttribute("businessBlockList", blockedBusinesses);
        return "/admin/business/BusBlockList";
    }
    
    //사업자 관리 상세
    @GetMapping("/business/blockManager/details/{userId}")
    public String businessBlockManagerDetails(@PathVariable String userId, Model model) {
        BusinessDto businessDto = businessDao.getBusinessDetails(userId);
        model.addAttribute("business", businessDto);
        return "/admin/business/BusBlockDetail";
    }
    
    //사업자 차단, 해제 
    @PostMapping("/business/blockManager/details/{userId}")
    public String busBlock(@RequestParam String busId,
                                @RequestParam String blockComment,
                                @RequestParam String blockStatus) {
        BusinessBlockDto blockDto = new BusinessBlockDto();
        blockDto.setBusId(busId);
        blockDto.setBusBlockComment(blockComment);
        blockDto.setBusBlockStatus(blockStatus);

        // adminDao를 직접 호출하여 업데이트
        adminDao.updateBusBlock(blockDto);
        System.out.println("상태: " + blockDto.getBusBlockStatus());
        return "redirect:/admin/business/blockManager/list";
    }
    
  




    
    
//    
 // 이용자 차단 구문 
 	@RequestMapping("customer/block")
 	public String cusBlock(@RequestParam String customerId) {
 		customerDao.insertBlock(customerId);
     		return "redirect:list";
 	}
 	
 	
 	@RequestMapping("customer/cancle")
 	public String cusCancel(@RequestParam String customerId) {
 		customerDao.deleteBlock(customerId);
 		return "redirect:list";
 	}

 	
 	

 	
    // 이용자 차단 관리자 목록 
 	@RequestMapping("/customer/list")
 	public String list(@ModelAttribute("vo") CusAdminVO vo, Model model) {
 	    List<CustomerAdminListDto> list = customerDao.cusAdminList(vo); // 해당 레코드를 가져옴
 	    log.debug("list ={}s", list);
 	    model.addAttribute("list", list);
 	    return "/customer/list";
 	}   

    
    // 이용자 차단 상세 
    @RequestMapping("/customer/detail")
    public String detail(@RequestParam String customerId, Model model) {
    	
    	CustomerBlockDto customerBlockDto = customerDao.selectBlockOne(customerId);
    	log.debug("customerBlockDto ={}s", customerBlockDto);
    	model.addAttribute("customerBlockDto", customerBlockDto);
    	
    	
    	CustomerAdminListDto customerAdminListDto = customerDao.cusAdminOne(customerId);
    	log.debug("customerAdminListDto ={}s", customerAdminListDto);
    	model.addAttribute("customerAdminListDto", customerAdminListDto);
    
    	CustomerDto customerDto = customerDao.selectOne(customerId);
    	log.debug("customerDto ={}s", customerDto);
    	model.addAttribute("customerDto", customerDto);
    	
    	
    	return "/customer/detail";
    	}
	}




