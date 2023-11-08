<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<form action="join" method="post" autocomplete="off">
	<div class="row">
		<div class="col">
			아이디 : <input type="text" name="customerId">
			<br>
			비밀번호 : <input type="password" name="customerPw">
			<br>
			이름 : <input type="text" name="customerName">
			<br>
			이메일 : <input type="text" name="customerEmail">
			<br>
			연락처 : <input type="tel" name="customerContact">
			<br>
			생년월일 : <input type="date" name="customerBirth">
			<br>
			성별 : <input type="text" name="customerGender">
			<br>
		</div>
		
		<div class="col">
			<button type="submit">
			가입하기
			</button>
		</div>
	</div>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>