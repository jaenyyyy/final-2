<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-700">
	<div class="row">
		<h1>${customerDto.customerId} 님 반갑습니다.</h1>
	</div>
	
	<div class="row">
		<table class="table table-border table-stripe">
			<tr>
				<th width="25%">이름</th>
				<td>${customerDto.customerName}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${customerDto.customerEmail}</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${customerDto.customerContact}</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>${customerDto.customerBirth}</td>
			</tr>
			
			<tr>
				<th>등급</th>
				<td>${customerDto.customerLevel}</td>
			</tr>
			
	
		</table>
		
			<a href="logout"> 로그아웃 </a>
			<a href="changePw"> 비밀번호 변경 </a>
			<a href="change"> 개인정보 변경 </a>
			<a href="exit">회원 탈퇴</a>
			
			
			<a href="rezList">예약내역</a>
			<a href="reviewListByCus">나의리뷰</a>
			
	</div>
	
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




