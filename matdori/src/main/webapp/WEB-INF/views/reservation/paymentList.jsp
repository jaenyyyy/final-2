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
		<div class="row justify-content-center mb-4">
			<div class="col-md-6 mt-4">
				<h1 class="text-center ">
					<i class="fa-solid fa-check" style="color: #ffb416;"></i>예약완료
				</h1>
			</div>
		</div>



		<!-- 주내용 -->
		<div class="row justify-content-center text-center">

			<div class="col-md-8 list-border">
				<div>
					<h5 class="h-title">예약내역</h5>
				</div>
				<div>
					<table class="table table-border table-stripe">
						<tr>
							<th>예약자</th>
							<td>${customerDto.customerName}</td>
						</tr>
						<tr>
							<th>예약인원</th>
							<td>${reservationDto.rezCustomerCount}명</td>
						</tr>
						<tr>
							<th>예약 일자</th>
							<td>${fn:substring(selectedClock.clockSelect, 0, 10)}</td>
						</tr>
						<tr>
							<th>예약 시간</th>
							<td>${fn:substring(selectedClock.clockSelect, 11, 12)}</td>
						</tr>
						<tr>
							<th>예약 메뉴</th>
							<td colspan="2">
								<table style="margin: 0 auto; text-align: center;">
									<!-- 스타일 추가 -->
									<c:forEach var="menuInfo" items="${menuInfo}">
										<tr>
											<td>${menuInfo.menuName}(${menuInfo.menuQty}개)</td>
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

		<div class="row mt-4">
			<div class="col">
				<button class="btn btn-warning">예약취소</button>
				<button class="btn btn-secondary">목록으로</button>
			</div>
		</div>





	</div>

</div>






<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
