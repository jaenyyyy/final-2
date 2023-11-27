//package com.kh.matdori.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.kh.matdori.error.AuthorityException;
//
//
///** 
// * 관리자 외 접근 차단
// */
//@Component
//public class AdminInterceptor implements HandlerInterceptor{
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		
//		// session의 level 항목이 관리자인지 확인하여 통과 및 차단 
//		HttpSession session = request.getSession();
//		String level = (String) session.getAttribute("level");
//		
//		boolean isAdmin = level != null && level.equals("관리자");
//		
//		if(isAdmin) { // 관리자 
//			return true;
//		}
//		else { // 관리자 아니면 (나무수저, 은수저, 금수저, 다이아수저, 맛도리수저) 
//			throw new AuthorityException("관리자만 이용 가능");
//		}
//	}
//}
//		
//
//
//	
