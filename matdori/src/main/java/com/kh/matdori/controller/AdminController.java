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
import com.kh.matdori.dao.HashtagDao;
import com.kh.matdori.dao.RestaurantDao;
import com.kh.matdori.dto.BusinessBlockDto;
import com.kh.matdori.dto.BusinessDto;
import com.kh.matdori.dto.BusinessJudgeDto;
import com.kh.matdori.dto.BusinessJudgeListDto;
import com.kh.matdori.dto.CustomerAdminListDto;
import com.kh.matdori.dto.CustomerBlockDto;
import com.kh.matdori.dto.CustomerDto;
import com.kh.matdori.dto.HashtagDto;
import com.kh.matdori.dto.RestaurantAdminListDto;
import com.kh.matdori.dto.RestaurantBlockDto;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.dto.RestaurantJudgeDto;
import com.kh.matdori.vo.BusBlockPaginationVO;
import com.kh.matdori.vo.BusPaginationVO;
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
   
   @Autowired
   private HashtagDao hashtagDao;
   
   
   @RequestMapping("/")
   public String home() {
      return "/admin/home";
   }
   

   //레스토랑 관리자 시점 (+차단 +심사 리스트)
   @RequestMapping("/restaurant/list")
   public String list(@ModelAttribute("vo") ResAdminVO vo, Model model) {
   	int count = adminDao.countList(vo);
   	vo.setCount(count);
   	
   	List <RestaurantAdminListDto> list = adminDao.resAdminListPaging(vo);
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
      
      RestaurantJudgeDto restaurantJudgeDto = adminDao.selectOne(resNo);
      model.addAttribute("restaurantJudgeDto", restaurantJudgeDto);
      
      return "/admin/restaurant/detail";
   }
   
   //관리자 시점 해시태그 관리
   @RequestMapping("/restaurant/hashtag")
   public String list(Model model) {
   	List<HashtagDto> hashtagList = hashtagDao.hashList();
       model.addAttribute("hashtagList", hashtagList); 
       return "/admin/restaurant/hashtag"; 
   }
   
//   //심사 리스트
   @GetMapping("/business/judge/list")
   public String busJudgeList(Model model, @ModelAttribute(name = "vo") BusPaginationVO vo) {
       int count = adminDao.countList(vo);
       vo.setCount(count);
       
       // 페이징 정보 계산
       vo.calculatePageInfo();
       
       List<BusinessJudgeListDto> businessJudgeList = adminDao.getList(vo);
       
       model.addAttribute("vo", vo);
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
        return "redirect:/admin/business/judge/list";
    }
    

    
    
    
    
  //사업자 차단 관리 리스트
    @GetMapping("/business/blockManager/list")
    public String businessBlockManagerList(Model model, @ModelAttribute(name = "vo") BusBlockPaginationVO vo) {
        int count = adminDao.countBlockList(vo);
        vo.setCount(count);
       // 페이징 정보 계산
       vo.calculatePageInfo(); 
       
       List<BusinessBlockDto> blockedBusinesses = adminDao.getBusBlocklist(vo);
       
       model.addAttribute("vo", vo);
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
        
        adminDao.updateBusBlock(blockDto);
        
        return "redirect:/admin/business/blockManager/list";
    }
    
  
   
    


    
    // 이용자 차단 관리자 목록 
    @RequestMapping("/customer/list")
    public String list(@ModelAttribute("vo") CusAdminVO vo, Model model) {
        List<CustomerAdminListDto> list = customerDao.cusAdminList(vo); // 해당 레코드를 가져옴
        model.addAttribute("list", list);
        
       int count = customerDao.countList(vo);
       vo.setCount(count);
       model.addAttribute("vo", vo);
      
        return "/admin/customer/list";
    }   

    
    // 이용자 차단 상세 
    @RequestMapping("/customer/detail")
    public String detail(@RequestParam String customerId, @ModelAttribute CusAdminVO vo, Model model) {
       
       CustomerBlockDto customerBlockDto = customerDao.selectBlockOne(customerId);
       model.addAttribute("customerBlockDto", customerBlockDto);
       
       
       CustomerAdminListDto customerAdminListDto = customerDao.cusAdminOne(customerId);
       model.addAttribute("customerAdminListDto", customerAdminListDto);
    
       CustomerDto customerDto = customerDao.selectOne(customerId);
       model.addAttribute("customerDto", customerDto);
       
       int count = customerDao.countList(vo);
       vo.setCount(count);
       model.addAttribute("vo", vo);
       
       return "/admin/customer/detail";
       }
   


//   // 이용자 등급 수정 ajax로 restcontroller로 옮김
//   @RequestMapping("/customer/levelEdit")
//   public String customerEdit(@ModelAttribute CustomerDto customerDto) {
//      boolean result = customerDao.updateCustomerLevel(customerDto);
//      if(result) {
//         return "redirect:edit?customerId="+customerDto.getCustomerId();
//      }
//      else {
//         return "redirect:error";
//      }
//   }
    
}


