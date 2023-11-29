//package com.kh.matdori.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.kh.matdori.interceptor.AdminInterceptor;
//import com.kh.matdori.interceptor.CustomerInterceptor;
//import com.kh.matdori.interceptor.NoticeAdminInterceptor;
//import com.kh.matdori.interceptor.PickInterceptor;
//import com.kh.matdori.interceptor.QnaAdminInterceptor;
//import com.kh.matdori.interceptor.ReservationInterceptor;
//import com.kh.matdori.interceptor.ReviewWriterInterceptor;
//
//@Configuration 
//public class InterceptorConfiguration implements WebMvcConfigurer {
//	
//	@Autowired
//	private AdminInterceptor adminInterceptor;
//	
//	@Autowired
//	private CustomerInterceptor customerInterceptor;
//	
//	@Autowired
//	private NoticeAdminInterceptor noticeAdminInterceptor;
//	
//	@Autowired
//	private PickInterceptor pickInterceptor;
//	
//	@Autowired
//	private QnaAdminInterceptor qnaAdminInterceptor;
//	
//	@Autowired
//	private ReservationInterceptor reservationInterceptor;
//	
//	@Autowired
//	private ReviewWriterInterceptor reviewWriterInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		
//		//admin
//		registry.addInterceptor(adminInterceptor)
//		.addPathPatterns(
//				"/admin/**"
//				);
//		//review 글 소유자 외 접근 차단
//		registry.addInterceptor(reviewWriterInterceptor)
//				.addPathPatterns(
//						"/review/edit",
//						"/review/delete"
//						);
//		
//		//customer 로그인 or 비로그인 시 
//		registry.addInterceptor(customerInterceptor)
//				.addPathPatterns(
//						"/customer/**",
//						"/review/write",
//						"/payment/**"
//						)
//				.excludePathPatterns(
//						"/customer/join",
//						"/customer/joinFinish",
//						"/customer/login",
//						"/customer/logout",
//						"/customer/exit/",
//						"/customer/findPw",
//						"/customer/findPwFinish"
//						);
//
//	}
//}
