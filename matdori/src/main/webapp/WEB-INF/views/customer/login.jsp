<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="login" method="post" autocomplete="off">
	<div class="container">
		<div class="row mv-30">
			<h1>로그인</h1>
		</div>
		<div>
			<input class="form-control" type="text" name="customerId" placeholder="아이디" required>
		</div>
		<div>
			<input type="password" name="customerPw" class="form-control" placeholder="비밀번호" required>
		</div>
		<div class="row mv-30"> 
			<button type="submit" class="btn btn-warning">
				<i class="fa-solid fa-user"></i>
				Login
			</button>
			<br>
		</div>
		<div class="text-center">
			<a href="customer/join"> 회원가입하기 </a>
		</div>
	</div>


</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


