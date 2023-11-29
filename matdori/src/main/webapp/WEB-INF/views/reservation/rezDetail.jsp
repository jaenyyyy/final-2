<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.h-title {
	font-weight: bold;
	color: #ffb416;
}

.list-border {
	border: 2px solid #ffb416;
	border-radius: 10px;
	padding: 20px;
}

.res-line {
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
					<div class="col-3">사진자리</div>
					<div class="col-7">
						<input type="hidden" class="resNoInput"
							value="${reservaitonDto.resNo}">
						<div class="row">${restaurantDto.resName}</div>
						<div class="row">예약일 :
							${fn:substring(selectedClock.clockSelect, 0, 10)}</div>
						<div class="row">예약시간 : 
						18 : 00
<%-- 							${fn:substring(selectedClock.clockSelect, 11, 16)} --%>
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
							${customerDto.customerName}</div>
						<div class="col">${customerDto.customerContact}</div>
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
								<td>${reservationDto.rezCustomerCount}명</td>
							</tr>
							<tr>
								<th>예약 일자</th>
								<td>${fn:substring(selectedClock.clockSelect, 0, 12)}</td>
							</tr>
							<tr>
								<th>예약 시간</th>
								<td>
								18시 00분
<%-- 								${fn:substring(selectedClock.clockSelect, 11, 12)} --%>
								</td>
							</tr>
							<tr>
								<th>예약 메뉴</th>
								<td colspan="2">
									<table style="margin: 0 auto; text-align: center;">
										<!-- 스타일 추가 -->
										<c:forEach var="menuInfo" items="${menuInfo}">
											<tr>
												<td>${menuInfo.menuName}(${menuInfo.menuQty} 개 )</td>
											</tr>
										</c:forEach>
									</table>
								</td>
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
					<h5 class="h-title" style="margin-top: 20px;">결제정보</h5>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>합계 금액</th>
							<td><fmt:formatNumber value="${sumTotal}" pattern="#,##0" />
								원</td>
						</tr>
					</table>
				</div>

				<div class="row">
					<div class="col mt-2" style="font-weight: bold;">결제방식</div>
					<div class="col">
						<img src="/images/kakao.png" width="80px">
					</div>
				</div>
				<div>
					<form id="paymentForm" method="post">
						<input type="hidden" name="rezNo" id="hiddenRezNo"> <input
							type="hidden" name="paymentTotal" id="hiddenPaymentTotal">

						<button type="submit" class="btn btn-warning w-100"
							style="margin-top: 70px;">결제하기</button>
					</form>
				</div>
			</div>


		</div>

	</div>
</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>