<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.container {
	border: 1.5px solid #ffb416;
	border-radius: 20px;
	padding: 5px 25px 20px;
	margin: 0 auto 10px;
}
</style>

<div class="container justify-content-center" style="margin-bottom: 6%;">




	<div class="row" style="margin-top: 10%;">
		<h1>
			<span style="font-weight: bold;">matdori</span>
		</h1>
	</div>

	<!-- 제목 -->
	<div class="row" style="margin-top: 10%;">
		<h2>
			<span style="font-weight: bold;">Change Finish</span> <i
				class="fa-regular fa-circle-check"></i>
		</h2>

		<br>
		<div class="row line"></div>
		<br> <br> <br>
	</div>
	<div class="row">
		<h5>비밀번호가 변경되었습니다.</h5>
	</div>
	<br> <br>
	<div class="row">
		<h5>
			<a href="login">login</a>
		</h5>
	</div>
	<div class="row">
		<h5>
			<a href="${pageContext.request.contextPath}/">home</a>
		</h5>
	</div>
	<div class="row">
		<h5>
			<a href="mypage"></a>
		</h5>
	</div>


</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>