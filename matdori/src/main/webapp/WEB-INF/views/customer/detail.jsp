<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
<h1> 회원 상세 </h1>
	
	<h1>${customerDto.customerId} 님의 회원 정보</h1>
	
	
	<div class="row mt-2">
		<div class="col-4"> 아이디</div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerDto.customerId}">
	</div>
</div>

<div class="row mt-2">
		<div class="col-4"> 이름 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerDto.customerName}">
	</div>

<div class="row mt-2">
		<div class="col-4"> 연락처 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerDto.customerContact}">
	</div>

<div class="row mt-2">
		<div class="col-4"> 이메일 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerDto.customerEmail}">
	</div>
	
	<div class="row mt-2">
		<div class="col-4"> 차단상태 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerBlockDto.customerStatus}">
	</div>
	
	<div class="row mt-2">
		<div class="col-4"> 차단시각 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerBlockDto.customerBlockTime}">
	</div>
	
	
	<div class="row mt-2">
		<div class="col-4"> 차단여부 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerAdminListDto.customerBlock}">
	</div>
	
	
<div class="row mt-2">
		<div class="col-4"> 차단사유 </div>
	</div>
	
	<div class="col-7">
		<input type="text" class="form-control" value="${customerBlockDto.customerBlockComment}">
	</div>
	


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>