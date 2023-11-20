<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>





<div class="row" style="margin-top: 2%">
	<div class="col">
	
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-regular fa-credit-card" style="color: #ffb416;"></i>결제하기
				</h1>
				<div class="row text-center">
					<div class="col">
						<table class="table table-border table-stripe">
						<tr>
							<th width="25%">예약 번호</th>
							<td>${rezDto.rezNo}</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td>${rezDto.rezCustomerId}</td>
						</tr>
						<tr>
							<th>매장명</th>
							<td>${rezDto.resName}</td>
						</tr>
						<tr>
							<th>예약 시간</th>
							<td>
							${fn:substring(rezDto.clockSelect, 0, 16)}
							</td>
						</tr>
						<tr>
							<th>좌석</th>
							<td>${rezDto.seatName}</td>
						</tr>
						<tr>
							<th>인원수</th>
							<td>${rezDto.rezCustomerCount}</td>
						</tr>
						<tr>
							<th>좌석수</th>
							<td>${rezDto.rezSeatQty}</td>
						</tr>
						<tr>
							<th>메뉴</th>
							<td>${rezDto.menuName}</td>
						</tr>
						<tr>
							<th>가격</th>
							<td>
							<fmt:formatNumber value="${rezDto.menuPrice}" 
									pattern="#,##0"/> 원
							</td>
						</tr>
						<tr>
							<th>메뉴 수량</th>
							<td>${rezDto.rezMenuQty}</td>
						</tr>
						<tr>
							<th>요청사항</th>
							<td>${rezDto.rezRequest}</td>
						</tr>
						<tr>
							<th>총 합계금액</th>
							<td>
							<fmt:formatNumber value="${rezDto.getTotal()}" 
									pattern="#,##0"/> 원
							</td>
						</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- 매장 정보 -->
		
		
		<!-- 예약자 정보 -->
	
		<!-- 예약 내역 -->
	
		<!-- 결제 정보 -->
		
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>