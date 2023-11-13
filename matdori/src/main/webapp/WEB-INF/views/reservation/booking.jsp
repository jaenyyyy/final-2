<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    
<form action="booking" method="post" >
	아이디<input type="text" name="rezCustomerId">
	매장번호<input type="number" name="rezResNo">
	메뉴번호<input type="number" name="rezMenuNo">
	ok번호<input type="number" name="okNo">
	인원수<input type="number" name="rezCustomerCount">
	요청사항<input type="text" name="rezRequest">
	<button type="submit">예약하기</button>
	
</form>
    
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>