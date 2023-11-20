<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>





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
		
		<div class="row text-center">
			<div class="col">
				<div>${rezDto.rezNo}</div>
				<div>${rezDto.customerId}</div>
				<div>${rezDto.resName}</div>
				<div>${rezDto.menuName}</div>
				<div>${rezDto.menuPrice}</div>
				<div>${rezDto.clockSelect}</div>
				<div>${rezDto.seatName}</div>
				<div>${rezDto.rezMenuQty}</div>
				<div>${rezDto.rezCustomerCount}</div>
				<div>${rezDto.rezSeatQty}</div>
				<div>${rezDto.getTotal()}</div>
			</div>
		</div>
		
		<!-- 매장 정보 -->
		
		
		<!-- 예약자 정보 -->
	
		<!-- 예약 내역 -->
	
		<!-- 결제 정보 -->
		
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>