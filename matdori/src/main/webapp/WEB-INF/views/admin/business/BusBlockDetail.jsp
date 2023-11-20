<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container mt-5">
	<h1>사업자 상세 정보</h1>

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
	
<!-- 차단/해제 폼 -->
    <form id="blockForm" action="/admin/business/blockManager/details/${business.busId}" method="post">
        <input type="hidden" name="busId" value="${business.busId}">
        
        <div class="row mt-2">
            <div class="col-4">차단사유</div>
        </div>
        <div class="col-8">
            <input type="text" class="form-control" name="blockComment" id="blockComment">
        </div>
        
        <div class="row mt-2">
            <div class="col-12">
                <button type="submit" name="blockStatus" value="Y" class="btn btn-danger">차단</button>
                <button type="submit" name="blockStatus" value="N" class="btn btn-success">해제</button>
                <a href="/admin/business/blockManager/list" class="btn btn-primary">목록으로</a>
            </div>
        </div>
    </form>


</div>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
