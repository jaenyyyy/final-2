<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

.rating {
	width: 100%;
}

.rating__star {
	cursor: pointer;
	color: #FFB416;
}
</style>

<div class="container">
	<div class="row" style="margin-top: 5%">

		<!-- 제목 -->
		<div class="row mb-4">
			<h1 class="bold">
				<i class="fa-solid fa-user" style="color: #ffb416;"></i> 마이페이지
			</h1>
		</div>

		<!-- 마이페이지 탭 -->
		<div class="col-3 page-line">
			<div class="row">
				<h2 class="bold">
					<a class="menu-tag" style="color: #FFB416;" href="/customer/pick">북마크</a>
				</h2>
			</div>
			<div class="row row-top">
				<h2 class="bold">
					<a class="menu-tag" href="/customer/rezList">예약내역</a>
				</h2>
			</div>
			<div class="row row-top">
				<h2 class="bold">
					<a class="menu-tag" href="">나의리뷰</a>
				</h2>
			</div>
		</div>



		<!-- 주 내용 -->
		<div class="col-9">
			<div class="row">
				<c:forEach var="RestaurantDto" items="${pickList}">
					<div>
						<br>
					</div>
					<div class="row res-line p-2" style="margin-left: 100px;">
						<div class="col">
							<div class="row modal-title">${RestaurantDto.resName}
							</div>
							<div class="row">전화 번호 :
								${RestaurantDto.resTel}</div>
							<div class="row">위치 :
								${RestaurantDto.resAddr1}</div>
						</div>
						<div class="col-2 text-end">
							<div class="row mb-3">
								<a href="#" class="menu-tag">상세정보 ></a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
</div>





<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>