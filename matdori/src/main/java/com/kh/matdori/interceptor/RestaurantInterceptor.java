//package com.kh.matdori.interceptor;
//
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.kh.matdori.dao.RestaurantDao;
//import com.kh.matdori.dto.RestaurantDto;
//
///**
// * 식당 차단 인터셉터
// */
//@Component
//public class RestaurantInterceptor  implements HandlerInterceptor{
//	
//	@Autowired
//	private RestaurantDao restaurantDao;
//	
//	   @Override
//	    public boolean preHandle(HttpServletRequest request,
//	    				HttpServletResponse response, Object handler) throws Exception {
//	        // 식당 번호를 요청 파라미터로 받거나 적절한 방법으로 가져와야 합니다.
//	        int restaurantNumber = Integer.parseInt(request.getParameter("resNo"));
//
//	        // 식당 번호로 식당 정보 조회
//	        RestaurantDto restaurant = restaurantDao.selectOne(restaurantNumber);
//
//	        if (restaurant != null && isRestaurantBlocked(restaurant)) {
//	            // 차단된 식당인 경우 처리 (예: 페이지 또는 메시지 출력)
//	            response.sendRedirect("/blocked-restaurant-page");
//	            return false; // 차단된 식당으로의 접근을 막기 위해 false 반환
//	        }
//
//	        return true; // 차단되지 않은 경우에만 true 반환하여 요청을 계속 진행
//	    }
//
//	    private boolean isRestaurantBlocked(RestaurantDto restaurant) {
//	        // 여기서 식당이 차단되어 있는지 여부를 확인하는 로직을 작성해야 합니다.
//	        // 예를 들어, 식당의 상태를 확인하여 차단 여부를 결정할 수 있습니다.
//	        // 차단 여부를 확인하는 조건에 맞게 구현해야 합니다.
//	        return restaurant.isBlocked(); // 예시로 isBlocked() 메서드가 있다고 가정
//	        // ++ 차단 구문 따로 만들어야 할 것 같습니다 
//	    }
//	}
//
