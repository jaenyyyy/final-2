package com.kh.matdori.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.matdori.error.AuthorityException;

/**
 * 예약 차단 인터셉터
 */
@Component
public class ReservationInterceptor implements HandlerInterceptor {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		String customerId = (String) session.getAttribute("name");
		boolean isLogin = customerId != null;
		if(isLogin) { // 회원 
			return true;
		}
		else { // 비회원 
			// 차단 + 로그인페이지로 리다이렉트
			// response.sendRedirect("customer/login");
			
			// 권한 없음 오류 발생
			// response.sendError(401);
			// return false;
			throw new AuthorityException("로그인 후 이용해주세요");
		}
	
	}
}