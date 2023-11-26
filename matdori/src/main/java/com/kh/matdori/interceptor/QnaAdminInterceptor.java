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
///**
// * QNA 인터셉터
// */
//@Component
//public class QnaAdminInterceptor  implements HandlerInterceptor{
//	@Override
//	public boolean preHandle(
//			HttpServletRequest request, 
//			HttpServletResponse response, 
//			Object handler)
//			throws Exception {
//		
//		HttpSession session =request.getSession();
//		
//		String level =(String) session.getAttribute("level");
//		boolean isAdmin =level.equals("관리자");
//		
//		if(isAdmin) {
//			return true;
//		}
//		else {
//			
//			throw new AuthorityException("관리자만 Qna에 접근");
//		}
//	}
//}