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
					<i class="fa-solid fa-check" style="color: #ffb416;"></i> 예약내역
				</h1>
			</div>
		</div>



		<!-- 주내용 -->
		<div class="row justify-content-center text-center">

			<div class="col-md-8 list-border">
				<div>
					<h5 class="h-title">예약정보</h5>
				</div>
				<div>

					<table class="table table-border table-stripe">
						<tr>
							<th>매장이름</th>
							<td>${rezDetailListDto.resName}</td>
						</tr>
						<tr>
							<th>예약자</th>
							<td>${rezDetailListDto.customerName}</td>
						</tr>
						<tr>
							<TH>예약자 번호</TH>
							<TD>${rezDetailListDto.customerContact}</TD>
						</TR>
						<tr>
							<th>예약 일자</th>
							<td>${rezDetailListDto.paymentTime}</td>
						</tr>
						<tr>
							<th>예약 시간</th>
							<td>${rezDetailListDto.paymentTime}</td>
						</tr>
						<tr>
							<th>결제 금액</th>
							<td>${rezDetailListDto.paymentPrice}</td>
						</tr>
						<tr>
							<th>예약 상태</th>
							<c:choose>
								<c:when test="${rezDetailListDto.paymentStatus == '예약취소'}">
									<td style="color: red;">${rezDetailListDto.paymentStatus}</td>
								</c:when>
								<c:otherwise>
									<td>${rezDetailListDto.paymentStatus}</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col">
				<c:choose>
					<c:when test="${rezDetailListDto.paymentStatus == '예약완료'}">
						<a href="cancel?paymentNo=${rezDetailListDto.paymentNo}"
							class="btn btn-warning"
							onclick="return confirm('예약을 취소하시겠습니까?')">예약취소</a>
					</c:when>
					<c:otherwise>
						<button class="btn btn-warning" disabled>취소완료</button>
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/customer/rezList" class="btn btn-secondary">목록으로</a>
			</div>
		</div>





	</div>

</div>






<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
