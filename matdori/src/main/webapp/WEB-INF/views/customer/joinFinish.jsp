<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>

.border-line {
    border: 1px solid #000;
    padding: 20px;
}
.container {
    border: 1px solid #ffb416; /* 테두리 스타일 및 색상 설정 */
    border-radius: 15px; /* 테두리 모서리를 둥글게 만듭니다 */
    padding: 40px 100px 50px; /* 내부 여백 설정 - 여기서 첫 번째 값은 상단 패딩입니다 */

    margin: 0 auto 20px; /*
}
</style>
<br><br><br>

<div class="container justify-content-center"
		style="margin-bottom: 20%;">

<div class="row" style="margin-top: 20%;">
<h1><span style="font-weight: bold;">matdori</span></h1> </div>

		<!-- 제목 -->
		<div class="row" style="margin-top: 20%;">
			<h2>
			<span style="font-weight: bold;">Join Finish</span>
			<i class="fa-regular fa-circle-check"></i>
			</h2>
	
		<br>
		<div class="row line"></div>
		<br><br><br>
	</div>
	<div class="row">
		<h5>회원가입에 감사드립니다 </h5>
	</div><br><br>
	<div class="row">
		<h5><a href="login">login</a></h5>
	</div>
	<div class="row">
		<h5><a href="/">home</a></h5>
	</div>
	</div>
	
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>