<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="login" method="post" autocomplete="off">
<div class="container w-400">
	<div class="row mv-30">
		<h1>로그인</h1>
	</div>
	<div class="row">
		<input type="text" name="customerId" placeholder="아이디" required
				class="form-input w-100">
	</div>
	<div class="row">
		<input type="password" name="customerPw" 
				class="form-input w-100" placeholder="비밀번호" required>
	</div>
	<div class="row mv-30"> 
		<button type="submit" class="btn btn-positive w-100">
			<i class="fa-solid fa-user"></i>
			Login
		</button>
		</div>


</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


