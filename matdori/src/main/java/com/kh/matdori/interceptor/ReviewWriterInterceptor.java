package com.kh.matdori.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.matdori.dao.ReviewDao;
import com.kh.matdori.dto.ReviewDto;

@Component
	public class ReviewWriterInterceptor implements HandlerInterceptor {

	    @Autowired
	    private ReviewDao reviewDao;

	    @Override
	    public boolean preHandle(
	    		HttpServletRequest request, 
				HttpServletResponse response, 
				Object handler) throws Exception {
	        HttpSession session = request.getSession();
	        String customerId = (String) session.getAttribute("name");

	        // 로그인 확인
	        if (customerId == null) {
	            // 비로그인 시 글쓰기, 수정하기 차단
	            response.sendRedirect("/login"); // 로그인 페이지로 리다이렉트 또는 에러 페이지 표시
	            return false;
	        }

	        // 추가 리뷰 금지 - 한 번 쓴 리뷰 추가로 못 쓰게 차단
	        int countOfReviews = reviewDao.getCountOfReviewsByCustomerId(customerId);
	        if (countOfReviews > 0) {
	            // 추가 리뷰 금지
	            // 추가 리뷰를 할 수 없는 에러 페이지 표시 또는 작업 중지
	            return false;
	        }

	        // 소유자 확인
	        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
	        ReviewDto reviewDto = reviewDao.selectOne(reviewNo);
	        if (reviewDto == null) {
	            // 존재하지 않는 리뷰 글
	            // 에러 페이지 표시 또는 작업 중지
	            return false;
	        }

	        // 소유자인지 확인하여 수정, 삭제 차단
	        if (!reviewDto.getReviewWriter().equals(customerId)) {
	            // 소유자가 아닌 경우
	            // 수정, 삭제 차단
	            // 에러 페이지 표시 또는 작업 중지
	            return false;
	        }

	        // 모든 조건 통과 시에만 계속 진행
	        return true;
	    }
	}


