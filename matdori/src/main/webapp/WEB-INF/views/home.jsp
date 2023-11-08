<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<h3> 맛도리 홈페이지 입니당 ~ </h3>
<br>
<a href="customer/mypage">마이페이지로</a>
<br>
<a href="customer/login"> 로그인하기 </a>
<br>
<a href="customer/logout">로그아웃</a>
<br>
<a href="customer/join"> 회원가입하기 </a>
<br>
<a href="customer/change">정보변경</a>
<br>
<a href="exit">회원 탈퇴</a>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>