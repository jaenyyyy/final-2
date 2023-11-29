package com.kh.matdori.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.matdori.dao.PickDao;
import com.kh.matdori.dto.RestaurantDto;
import com.kh.matdori.error.AuthorityException;

/**
 * 찜하기 차단 인터셉터 
 */
@Component
public class PickInterceptor implements HandlerInterceptor {
	
	@Autowired
	private PickDao pickDao;
	
	@Override
    public boolean preHandle(HttpServletRequest request, 
                            HttpServletResponse response, Object handler)
            throws Exception {
        
        HttpSession session = request.getSession();
        
        String customerId = (String) session.getAttribute("customerId");
        
        String[] pickNumbers = request.getParameterValues("resNo");
        
        List<RestaurantDto> pickItems = pickDao.pickList(customerId);
        
        for (String clientPickNumber : pickNumbers) {
            boolean isValidPickNumber = false;
            for (RestaurantDto pickItem : pickItems) {
                if (clientPickNumber.equals(String.valueOf(pickItem.getResNo()))) {
                    isValidPickNumber = true;
                    break;
                }
            }
            if (!isValidPickNumber) {
                throw new AuthorityException("찜 목록에 없는 예약입니다");
            }
        }
        
        return true;
    }

}
