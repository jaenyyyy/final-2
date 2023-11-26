<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/pwChange.js"></script>

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

<form action="changePw" method="post" autocomplete="off">


	<div class="container justify-content-center"
		style="margin-bottom: 6%;">


		<div class="row" style="margin-top: 20%;">
			<h1>
				<i class="fa-solid fa-circle-user" style="color: #ffb416;"></i>
				Change Pw
			</h1>
		</div>

		안전한 비밀번호로 내정보를 보호하세요 <br> 다른 아이디/사이트에서 사용한 적 없는 비밀번호<br> 이전에
		사용한 적 없는 비밀번호가 안전합니다.<br>
		<br>
		<div class="row line"></div>


		<div class="row mt-4">
			<input class="form-control" type="password" name="originPw"
				placeholder="현재 비밀번호">
		</div>

		<div class="row mt-4">
			<input class="form-control" type="password" name="changePw"
				placeholder="새 비밀번호">
			<div class="success-feedback">올바른 비밀번호 형식입니다</div>
			<div class="fail-feedback">잘못된 비밀번호 형식입니다</div>
			<div class="fail2-feedback">현재 비밀번호와 일치합니다</div>
		</div>

		<div class="row mt-4">
			<input class="form-control" type="password" id="changePw-check"
				placeholder="새 비밀번호 확인">
			<div class="success-feedback">비밀번호가 일치합니다</div>
			<div class="fail-feedback">바꿀 비밀번호와 일치하지 않습니다</div>
			<div class="fail2-feedback">바꿀 비밀번호를 먼저 작성하세요</div>
		</div>

		<br>
		<div class="row line"></div>
		<br>


		<div class="row mv-30 mt-4">
			<button type="submit" class="btn btn-warning text-black">
				비밀번호 변경</button>
		</div>
		<div class="row mt-4">	
			<a class="btn btn-outline-warning" href="mypage">취소</a>
		</div>
	</div>

<c:if test="${param.error !=null }">
	<div class="row important">
		<h3>기존 비밀번호가 일치하지 않습니다</h3>
	</div>
</c:if>


</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>