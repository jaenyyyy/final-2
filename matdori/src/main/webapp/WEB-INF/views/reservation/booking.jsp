<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>



<div class="row" style="margin-top:2%;">
	<div class="col">
		<form action="insert" method="post" >
		<div class="row justify-content-center">
			<div class="card mb-3" style="max-width:50rem;">
				<div class="card-body">
<%-- 					<input type="hidden" name="rezNo" value="${ReservationDto.rezNo}"> --%>
<!-- 					<label class="form-label mt-4" for="readOnlyInput">아이디</label> -->
<%-- 	    			<input type="hidden" name="rezCustomerId" value="${rezCustomerId}"> --%>
<!-- 					<label class="form-label mt-4" for="readOnlyInput">매장</label> -->
	    			<input type="hidden" name="rezResNo" value="${rezResNo}">
	    			
					<label class="form-label mt-4" for="readOnlyInput">시간</label>
	    			<select class="form-control" name="selectedClock">
				        <option>선택하세요</option>
				        <c:forEach var="clockDto" items="${clockList}">				        	
				            <option value="${clockDto.clockNo}">
				            <fmt:formatDate value="${clockDto.clockSelect}" pattern="yyyy-MM-dd HH"/>
				            </option>
				        </c:forEach>
				   	</select>
	    			
					<label class="form-label mt-4" for="readOnlyInput">좌석</label>
	    			<select class="form-control" name="selectedSeat">
	    				<option>선택하세요</option>
				        <c:forEach var="seatDto" items="${seatList}">
				            <option value="${seatDto.seatNo}">
				            ${seatDto.seatName}
				            </option>
				        </c:forEach>
				   	</select>
	    			
					<label class="form-label mt-4" for="readOnlyInput">인원수</label>
	    			<input class="form-control" type="number" name="rezCustomerCount">
	    			
					<label class="form-label mt-4" for="readOnlyInput">좌석수</label>
	    			<input class="form-control" type="number" name="rezSeatQty">
	    			
					<label class="form-label mt-4" for="readOnlyInput">메뉴</label><br>
			        <c:forEach var="MenuWithImagesVO" items="${menuList}">
			            <label>
			                <input type="checkbox" name="selectedMenus"
			                 value="${MenuWithImagesVO.menuNo}">
			                ${MenuWithImagesVO.menuName} (가격 : ${MenuWithImagesVO.menuPrice})
			            </label><br>
						<label class="form-label mt-1" for="readOnlyInput">수량</label>
		    			<input class="form-control" type="number" name="selectedQtys">
			            <br>
			        </c:forEach>
	    			
	    			
					<label class="form-label mt-4" for="readOnlyInput">요청사항</label>
	    			<input class="form-control" type="text" name="rezRequest">
	    			
					<button class="btn btn-warning w-100 mt-4" type="submit">예약하기</button>
				</div>
			</div>
		</div>
			
		</form>
	
	</div>
	</div>
    
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>