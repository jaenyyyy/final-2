<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="row">${reservationListDto.rezNo}</div>
<div class="row">${reservationListDto.customerId}</div>
<div class="row">${reservationListDto.resName}</div>
<div class="row">${reservationListDto.menuName}</div>
<div class="row">${reservationListDto.menuPrice}</div>
<div class="row">${reservationListDto.clockSelect}</div>
<div class="row">${reservationListDto.seatName}</div>
<div class="row">${reservationListDto.rezMenuQty}</div>
<div class="row">${reservationListDto.rezCustomerCount}</div>
<div class="row">${reservationListDto.rezSeatQty}</div>
<div class="row">${reservationListDto.getTotal()}</div>




<div class="row" style="margin-top: 2%">
	<div class="col">
	
		<!-- 이름 -->
		<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-regular fa-credit-card" style="color: #ffb416;"></i>결제하기
				</h1>
			</div>
		</div>
		
		
		<!-- 매장 정보 -->
		
		
		<!-- 예약자 정보 -->
	
		<!-- 예약 내역 -->
	
		<!-- 결제 정보 -->
		
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>