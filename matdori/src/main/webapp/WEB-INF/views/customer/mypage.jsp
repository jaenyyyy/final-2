
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.row-top {
	margin-top: 20%;
}

.page-line {
	border-right: 3px solid #ffb416;
}

.bold {
	font-weight: bold;
}

.menu-tag {
	text-decoration: none;
	color: black;
}

.menu-tag:hover {
	color: #FFB416;
}

.res-line {
	border-top: 1px solid #ffb416;
	border-bottom: 1px solid #ffb416;
	width: 70%;
}

.border-line {
	border: 2px solid #ffb416;
	border-radius: 10px;
}
</style>



<div class="container">
	<div class="row" style="margin-top: 5%">



		<!-- 마이페이지 탭 -->
		<div class="col-3 page-line">
			<div class="row">

				<!--  제목  -->
				<div class="row mb-4">
					<h2 class="bold">
						<a class="menu-tag" href="mypage"> <i class="fa-solid fa-user"
							style="color: #ffb416;"></i> 마이페이지
						</a>
					</h2>
				</div>
				<br> <br> <br> <br> <br> <br>

				<h4 class="bold">
					<a class="menu-tag" href="pick">북마크</a>
				</h4>
			</div>
			<div class="row row-top">
				<h4 class="bold">
					<a class="menu-tag" href="rezList">예약내역</a>
				</h4>
			</div>
			<div class="row row-top">
				<h4 class="bold">
					<a class="menu-tag" href="reviewListByCus">나의리뷰</a>
				</h4>
			</div>
		</div>



		<!-- 주 내용 -->
		<div class="col-8 border-line ms-4">
			<br>
			<div class="row ms-4 mt-4">
				<br> <br> <br>
				<h1>
					<span style="font-weight: bold;"> [
						${customerDto.customerId}</span> <span style="font-weight: bold;">
						] 님 반갑습니다.</span>
				</h1>
			</div>

			<div class="row ms-4 mt-4">
				<div class="col">
					<table class="table table-border table-stripe text-center">
						<tr>
							<th width="25%">이름</th>
							<td>${customerDto.customerName}</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${customerDto.customerEmail}</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>${customerDto.customerContact}</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>${customerDto.customerBirth}</td>
						</tr>

					</table>

				</div>
				<div class="col" style="margin-left: 40px; margin-right: 40px;">
					<div class="row mt-4">
						<a class="btn btn-outline-warning" href="change">개인정보 변경</a>
					</div>
					<div class="row mt-4">
						<a class="btn btn-outline-warning " href="changePw">비밀번호 변경</a>
					</div>
					<div class="row mt-4">
						<a class="btn btn-outline-warning" href="exit">맛도리 탈퇴</a>
					</div>
				</div>


			</div>

		</div>

	</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>