<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    
<form action="insert" method="post" >
	아이디<input type="text" name="rezCustomerId">
	매장<input type="number" name="rezResNo">
	시간<select name="selectedClock">
        <c:forEach var="clockDto" items="${clockList}">
            <option value="${clockDto.clockNo}">
            <fmt:formatDate value="${clockDto.clockSelect}" pattern="yyyy-MM-dd HH"/>
            </option>
        </c:forEach>
   	</select>
	좌석<select name="selectedSeat">
        <c:forEach var="seatDto" items="${seatList}">
            <option value="${seatDto.seatNo}">
            ${seatDto.seatName}
            </option>
        </c:forEach>
   	</select>
	인원수<input type="number" name="rezCustomerCount">
	좌석수<input type="number" name="rezSeatQty">
	메뉴<input type="number" name="rezMenuNo">
	메뉴수량<input type="number" name="rezMenuQty">
	요청사항<input type="text" name="rezRequest">
	<button type="submit">예약하기</button>
	
</form>
    
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>