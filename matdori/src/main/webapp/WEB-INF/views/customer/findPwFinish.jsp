<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

	<div class="container w-400">
		<div class="row">
			<h1>이메일로 비밀번호가 전송되었습니다</h1>
		</div>
		<div class="row">
			<a type="link" href="/customer/login">
				<button type="submit" class="btn btn-positive w-100">로그인 하러가기</button>
			</a>
		</div>
	</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
