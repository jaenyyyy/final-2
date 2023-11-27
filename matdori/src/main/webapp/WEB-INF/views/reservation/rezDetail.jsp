<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	.h-title{
		font-weight: bold;
		color: #ffb416;
	}
	.list-border{
		border: 2px solid #ffb416;
		border-radius: 10px;
		padding : 20px;
	}
	.res-line{
		border-top: 2px solid #ffb416;
		border-bottom: 2px solid #ffb416;
		width: 100%;
	}
</style>



<div class="container">
    <div class="col-md-8 mx-auto">
	
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-regular fa-credit-card" style="color: #ffb416;"></i>결제하기
				</h1>
			</div>
		</div>
		
		
		
		<!-- 주내용 -->		
		<div class="row justify-content-center text-center">
		
			<!-- 매장정보 -->
			<div class="row ms-4 mt-4 mb-4">
				
				<div class="row res-line p-3">
				        <div class="col-3">
				            사진자리
				        </div>
				        <div class="col-7">
				            <input type="hidden" class="resNoInput" value="${reservaitonDto.resNo}">
				            <div class="row">
				                ${restaurantDto.resName}
				            </div>
				            <div class="row">
				                예약일 : ${fn:substring(selectedClock.clockSelect, 0, 10)}
				            </div>
				            <div class="row">
				                예약시간 : ${fn:substring(selectedClock.clockSelect, 11, 16)}
				            </div>
				        </div>
				        
				    </div>
			</div>

			<div class="col">
				<!-- 예약자 정보 -->
				<div class="list-border mb-4">
					<div>
						<h5 class="h-title">예약자정보</h5>
					</div>
					<div class="row">
						<div class="col" style="font-weight: bold;">
							${customerDto.customerName} 
						</div>
						<div class="col">
							${customerDto.customerContact}
						</div>
					</div>
					
				</div>
				<!-- 예약내역 -->
				
				<div class="list-border">
					<div>
						<h5 class="h-title">예약내역</h5>
					</div>
					<div>
						<table class="table table-border table-stripe">
							<tr>
								<th>인원수</th>
								<td>${reservationDto.rezCustomerCount} 명</td>
							</tr>
							<tr>
								<th>예약 일자</th>
								<td>${fn:substring(selectedClock.clockSelect, 0, 10)}</td>
							</tr>
							<tr>
								<th>예약 시간</th>
								<td>${fn:substring(selectedClock.clockSelect, 11, 16)}</td>
							</tr>
							<tr>
						    <th>예약 메뉴</th>
							    <c:forEach var="menuInfo" items="${menuInfo}">
								    <tr>
								        <th>메뉴 이름</th>
								        <td>${menuInfo.menuName}</td>
								    </tr>
								    <tr>
								        <th>메뉴 수량</th>
								        <td>${menuInfo.menuQty}</td>
								    </tr>
								</c:forEach>
							</tr>
							<tr>
								<th>요청사항</th>
								<td>${reservationDto.rezRequest}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			
			
			
			<!-- 결제정보 -->
			
			<div class="col list-border mb-4 ms-4">
				<div>
					<h5 class="h-title">결제정보</h5>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>합계 금액</th>
							<td>
								<fmt:formatNumber value="${sumTotal}" pattern="#,##0"/> 원
							</td>
						</tr>
					</table>
				</div>
				
				<div>
					<table class="table table-border table-stripe">
						<tr>
					    <th>포인트 사용</th>
					    <td>
					        <input type="number" class="form-control w-50" id="inputPoint" name="inputPoint" oninput="updatePaymentTotal()" value="${inputPoint}">
					        <span id="customerPoint">보유 포인트: <fmt:formatNumber value="${CustomerDto.customerPoint}" pattern="#,##0"/> pt </span>
					    </td>
					</tr>
					<tr>
					    <th>결제금액</th>
					    <td id="paymentAmount">
					        <fmt:formatNumber value="${paymentTotal}" pattern="#,##0"/> 원
					    </td>
					</tr>
					</table>
				</div>
				
				<div class="row">
					<div class="col mt-2" style="font-weight: bold;">결제방식</div>
					<div class="col"><img src="/images/kakao.png" width="80px"></div>
				</div>
				<div>
					<input type="checkbox"> 동의하나요 <br>
					<input type="checkbox"> 동의하나요
				</div>
				<div>
					전송되는 부분
					<form method="post">
						<input type="hidden" name="restaurant.productNo" value="${paymentSumVO.reservationListDto.productNo}">
						<input type="hidden" name="restaurant.qty" value="${confirmVO.purchaseVO.qty}">
					<button type="submit" class="btn btn-warning w-100">결제하기</button>
					</form>
				</div>
			</div>
			
			
		</div>
		
	</div>
</div>
		
		
		
							<th>좌석</th>
							<td>${SeatDto.seatName}</td>
						
							<th>좌석수</th>
							<td>${rezDto.rezSeatQty}</td>
							
							
<script>
	//JavaScript
	function updatePaymentTotal() {
	    var inputPoint = parseFloat(document.getElementById("inputPoint").value); // 사용자가 입력한 포인트 값
	    var sumTotal = parseFloat("${sumTotal}"); // VO에서 전달된 총 금액
	    var paymentAmountElement = document.getElementById("paymentAmount");
	    var customerPoint = parseInt("${rezDto.customerPoint}");
	    
	    var paymentTotal = sumTotal - inputPoint; // 결제금액 계산
	    if (isNaN(inputPoint)) {
	        paymentTotal = sumTotal; // 입력값이 없을 경우 총 금액으로 설정
	    } else {
	        paymentTotal = sumTotal - inputPoint; // 입력값이 있을 경우 결제금액 계산
	        
	        if (inputPoint > customerPoint) {
	            alert("보유 포인트를 초과할 수 없습니다.");
	            document.getElementById("inputPoint").value = customerPoint;
	            inputPoint = customerPoint;
	            paymentTotal = sumTotal - inputPoint; // 초과한 경우 다시 계산
	        }
	        
	        if (paymentTotal < 0) {
	            paymentTotal = 0;
	        }
	    }

	    
	    // 결제금액을 화면에 업데이트
	    
	    var paymentAmountElement = document.getElementById("paymentAmount");
	    paymentAmountElement.textContent = paymentTotal.toLocaleString('ko-KR') + " 원";
	}

</script>
		
		

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>