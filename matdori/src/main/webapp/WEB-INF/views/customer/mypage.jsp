<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-500">
	<div class="row">
		<h1>${customerDto.customerId} 님의 회원 정보</h1>
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
			
			<a href="logout"> 로그아웃 </a>
			
				<div class="row">
		<a class="btn w-100" href="change">
			<i class="fa-solid fa-user"></i>
			개인정보 변경
		</a>
	</div>
		</table>
	</div>
	
	
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




