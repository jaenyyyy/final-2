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
	<div class="row border border-warning mt-4">
	<div class="col-md-8 mx-auto">

		<!-- 이름 -->
		<div class="row justify-content-center mb-4" style="margin-top:80px;">
			<div class="col mt-4">
				<h1 class="text-center ">
					<i class="fa-solid fa-check" style="color: #ffb416;"></i> 예약이 완료 되었습니다.
				</h1>
			</div>
		</div>



		<div class="row mt-4" style="margin-bottom:100px;">
			<div class="col">
				<a href="/customer/rezList" class="btn btn-secondary">목록으로</a>
				<a href="/paymentList" class="btn btn-warning">예약상세</a>
			</div>
		</div>



</div>

	</div>

</div>






<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
