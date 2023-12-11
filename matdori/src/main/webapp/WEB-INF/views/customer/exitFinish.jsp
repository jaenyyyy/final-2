<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<style>
.container {
	max-width: 500px; /* 변경 가능한 폭 설정 */
	margin: 0 auto; /* 가운데 정렬 */
}

.tag-none {
	text-decoration: none;
	color: black;
}

.line {
	border-top: 1px solid #ffb416;
}

.tag-none:hover {
	color: #FF3300; /* 원하는 호버 시 색상으로 변경 */
}
</style>


<form action="join" method="post" autocomplete="off">
	<div class="container justify-content-center"
		style="margin-bottom: 6%;">

		<!-- 제목 -->
		<div class="row" style="margin-top: 20%;">
			<h1>
				<i class="fa-solid fa-circle-user" style="color: #ffb416;"></i>
				회원탈퇴가 <br>완료되었습니다.
			</h1>
		</div>

		그동안 맛도리 사이트를 이용해주셔서 감사합니다. <br>
		<br>
		<br>
		<div class="row line"></div>
		<br>
		<br>
		<h5>
			<a href="${pageContext.request.contextPath}/">main page</a>
		</h5>
	</div>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>