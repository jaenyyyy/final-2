<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
	<h1>사업자 등록 심사 상세</h1>

	<div class="row mt-2">
		<div class="col-4 " >아이디</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busId}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">사업자 등록 번호</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busRegNo}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">사업자 이름</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busName}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">사업자 연락처</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busTel}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">사업자 이메일</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busEmail}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">우편번호</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busPost}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">기본 주소</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busAddr1}"
			readonly>
	</div>

	<div class="row mt-2">
		<div class="col-4">상세 주소</div>
	</div>
	<div class="col-8">
		<input type="text" class="form-control" value="${business.busAddr2}"
			readonly>
	</div>
	
<form id="judgeForm" action="/admin/business/details/{userId}" method="post">
    <input type="hidden" name="busId" value="${business.busId}">
    <input type="hidden" name="judgeStatus" id="judgeStatus" value="">
    <div class="row mt-2">
        <div class="col-4">심사코멘트</div>
    </div>
    <div class="col-8">
        <input type="text" class="form-control" name="judgeComment" id="judgeComment">
    </div>
    <button type="submit" name="judgeStatus" value="심사승인">승인</button>
    <button type="submit" name="judgeStatus" value="심사거절">거절</button>
</form>


</div>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
