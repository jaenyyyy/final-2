<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<div class="container justify-content-center" style="margin-bottom: 6%;">


	<div class="row" style="margin-top: 20%;">
		<h1>
			<i class="fa-solid fa-circle-user" style="color: #ffb416;"></i> 개인정보
			변경 완료!
		</h1>
	</div>
	<div class="row">
		<h3>
			<a href="${pageContext.request.contextPath}/">메인페이지로 이동</a>
		</h3>
	</div>
	<div class="row">
		<h3>
			<a href="mypage">마이페이지로 이동</a>
		</h3>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>