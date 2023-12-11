<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="row text-center" style="margin-left: 10%; margin-right: 10%">
	<div class="mt-5">
		<div class="mb-5">
			<h1>
				<i class="fa-solid fa-house" style="color: #ffb416;"></i><span
					style="font-weight: bold;"> 관리자 HOME</span>
			</h1>
		</div>
	</div>
	<div class="col-6">
		<div class="alert alert-dismissible alert-warning">
			<h3>
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/customer/list">이용자
					관리</a>
			</h3>
		</div>
	</div>
	<div class="col-6">
		<div class="alert alert-dismissible alert-warning">
			<h3>
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/restaurant/list">매장
					관리</a>
			</h3>
		</div>
	</div>
	<div class="col-6">
		<div class="alert alert-dismissible alert-warning">
			<h3>
				<a class="nav-link" href="/admin/business/blockManager/list">사업자
					관리</a>
			</h3>
		</div>
	</div>
	<div class="col-6">
		<div class="alert alert-dismissible alert-warning">
			<h3>
				<a class="nav-link" href="/admin/business/judge/list">사업자 심사</a>
			</h3>
		</div>
	</div>
	<div class="col-6">
		<div class="alert alert-dismissible alert-warning">
			<h3>
				<a class="nav-link" href="/admin/restaurant/hashtag">해시태그 관리</a>
			</h3>
		</div>
	</div>

</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>