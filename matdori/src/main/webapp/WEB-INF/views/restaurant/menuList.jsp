<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.card-img-top {
	width: 100%;
	height: 200px;
	object-fit: contain;
}
</style>

<!-- 메뉴 섹션 -->
<div class="container" id="fullPageContent">
	<div
		class="row justify-content-center text-center list-border info-margin"
		id="reviewSection">
		<!-- 메뉴 제목 -->
		<div class="row justify-content-between align-items-center">
			<div class="col-6">
				<h3>
					<span class="badge rounded-pill bg-warning text-black">메뉴</span>
				</h3>
			</div>
		</div>

		<!-- 메뉴 타입과 메뉴 리스트 -->
		<c:forEach var="entry" items="${menuListByType}">
			<div class="menu-type">
				<h4>
					<${entry.key.menuTypeName}>
					<hr>
				</h4>
				<!-- 메뉴 타입 이름 -->
				<div class="row">
					<c:forEach var="menu" items="${entry.value}">
						<!-- 메뉴 리스트 -->
						<div class="col-md-4">
							<div class="menu-item">
								<img class="card-img-top" src="${pageContext.request.contextPath}/image/menu/${menu.menuNo}"
									alt="${menu.menuName}">
								<h5>${menu.menuName}</h5>
								<p>${menu.menuContent}</p>
								<span>가격: ${menu.menuPrice}원</span>
							</div>
						</div>
					</c:forEach>
					<hr>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
