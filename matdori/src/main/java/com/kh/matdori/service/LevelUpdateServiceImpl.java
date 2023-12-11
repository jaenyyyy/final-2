package com.kh.matdori.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.matdori.dao.CustomerDao;
import com.kh.matdori.vo.CusLevelUpVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LevelUpdateServiceImpl implements LevelUpdateService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Scheduled(cron = "0 0 0 1 * ?")
	public void levelUp() {
		  List<CusLevelUpVO> cusLevelList = customerDao.successList();
		    for (CusLevelUpVO customer : cusLevelList) {
		        String customerId = customer.getCustomerId();
		        String customerLevel = customer.getCustomerLevel(); 

		        
		        if (!"관리자".equals(customerLevel)) {
		            int paymentSuccess = customer.getPaymentSuccess();
		            String newCustomerLevel = levelUpStandard(paymentSuccess);
		            boolean updated = customerDao.updateCustomerLevel(customerId, newCustomerLevel);
		            if (updated) {
		                customer.resetPaymentSuccess();
		            } else {
		            }
		        } else {
		        }
		    }
		}

	private String levelUpStandard(int paymentSuccess) {
	    if (paymentSuccess >= 15) return "맛도리수저";
	    if (paymentSuccess >= 10) return "다이아수저";
	    if (paymentSuccess >= 7) return "금수저";
	    if (paymentSuccess >= 4) return "은수저";
	    else return "나무수저";
}

}
