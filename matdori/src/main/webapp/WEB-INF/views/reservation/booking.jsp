<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    
<form action="booking" method="post" >
	아이디<input type="text" name="rezCustomerId">
	매장<input type="number" name="rezResNo">
	시간<input type="number" name="rezClockNo">
	좌석<input type="number" name="rezSeatNo">
	인원수<input type="number" name="rezCustomerCount">
	좌석수<input type="number" name="rezSeatQty">
	메뉴<input type="number" name="rezMenuNo">
	메뉴수량<input type="number" name="rezMenuQty">
	요청사항<input type="text" name="rezRequest">
	<button type="submit">예약하기</button>
	
</form>
    
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>