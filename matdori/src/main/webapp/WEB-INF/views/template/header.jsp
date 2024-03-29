
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<br>
<title>matdori</title>
<!-- 아이콘 사용을 위한 Font Awesome 6 CDN -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<link rel="stylesheet" type="text/css" href="/css/test/.css">

<!-- 구글 웹 폰트 사용을 위한 CDN -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/5.3.2/united/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- swiper cdn -->
<script
	src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />

<!--  css  -->
<link rel="stylesheet" type="text/css" href="/css/commons.css">
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>


</head>

<style>
/*a태그 밑줄제거*/
.dropdown-item a {
	text-decoration: none;
	color: black;
}

.nav-item a {
	font-weight: bold;
}

.icon-container {
	display: flex; /* Flexbox를 사용하여 요소들을 행으로 배치합니다 */
	gap: 20px; /* 아이콘 간의 간격을 조정합니다 */
	align-items: center; /* 수직 가운데 정렬을 위해 아이콘들을 수직으로 정렬합니다 */
}
</style>


<div class="container-fluid">

	<!-- 헤더 위 -->
	<div class="row mt-3 ms-4">
		<div class="col ms-4" style="margin-top: 70px;">


			<!-- 사업자 홈페이지 가는 주소 -->
			<div class="icon-container">
				<a href="${pageContext.request.contextPath}/busHome" class="me-4"> <i
					class="fa-solid fa-user-tie fa-3x" style="color: #ffb416;"></i>
				</a>
			</div>

		</div>


		<div class="col text-center">
			<a href="${pageContext.request.contextPath}/"> <img
				src="${pageContext.request.contextPath}/images/logo.png"
				style="width: 250px;" alt="맛도리 홈">
			</a>
		</div>
		<div class="col text-end me-4" style="margin-top: 70px;">
			<c:choose>
				<c:when test="${sessionScope.name != null}">
					<div class="dropdown">
						<a href="#" id="iconLink"> <i
							class="fa-solid fa-circle-user fa-3x" style="color: #ffb416;"></i>
						</a>
						<ul class="dropdown-menu" id="subMenu"
							style="position: absolute; top: 100%; right: 0;">
							<li class="dropdown-item"><a href="/customer/mypage">마이페이지</a></li>
							<li class="dropdown-item"><a href="/customer/pick">북마크</a></li>
							<div class="dropdown-divider"></div>
							<li class="dropdown-item"><a href="/customer/logout">로그아웃</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<a href="/customer/login"> <i
						class="fa-solid fa-right-to-bracket fa-3x" style="color: #ffb416;"></i>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- 메뉴바 -->
	<div class="row">
		<div class="col-md-12 offset mt-4">
			<nav class="navbar navbar-expand-lg bg-warning" data-bs-theme="light">
				<div class="container-fluid">
					<button class="navbar-toggler mb-2" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarColor03"
						aria-controls="navbarColor03" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-center"
						style="margin-left: 400px;" id="navbarColor03">
						<ul class="navbar-nav">
							<c:if test="${sessionScope.level == '관리자'}">
								<li class="nav-item"><a class="nav-link me-3"
									href="${pageContext.request.contextPath}/admin/">관리자</a></li>
							</c:if>
							<li class="nav-item"><a class="nav-link me-3"
								href="${pageContext.request.contextPath}/notice/list">공지사항</a></li>
							<li class="nav-item"><a class="nav-link me-3"
								href="${pageContext.request.contextPath}/qna/list">Q&A</a></li>

						</ul>
					</div>
					<div class="d-flex ms-auto">
						<form class="form-inline" id="searchForm"
							action="/restaurant/resSearchList" method="GET">
							<div class="col-auto">
								<div class="input-group">
									<input name="hashComment" class="form-control" type="search"
										placeholder="검색어를 입력하세요" autocomplete="off">
									<button id="searchBtn" class="btn btn-secondary" type="submit">Search</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</nav>
		</div>
	</div>
</div>

<script>
	document.addEventListener("DOMContentLoaded", function() {
		const iconLink = document.getElementById("iconLink");
		const subMenu = document.getElementById("subMenu");

		iconLink.addEventListener("click", function(event) {
			event.preventDefault();
			subMenu.classList.toggle("show");
		});

		document.addEventListener("click", function(event) {
			const isClickInside = iconLink.contains(event.target)
					|| subMenu.contains(event.target);
			if (!isClickInside) {
				subMenu.classList.remove("show");
			}
		});
	});
</script>

<section>