<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="row justify-content-center">
			<div class="col-md-6 mt-4">
			<br><br>
				<h1 class="text-center ">
					<i class="fa-solid fa-circle-check me-2" style="color: #ffb416;"></i>
					사업자 상세 정보 
				</h1>
				<br><br>
			</div>
		</div>
		
		
		<div class="row justify-content-center">
	<div class="card border-warning mb-3 " style="max-width: 50rem;">
	
				<div class="card-body">
					<label class="form-label mt-4" for="readOnlyInput">사업자 아이디</label> 
					<input
						class="form-control" id="readOnlyInput1" type="text"
						value="${business.busId}" disabled> <label
						class="form-label mt-4" for="readOnlyInput">사업자 번호</label> <input
						class="form-control" id="readOnlyInput2" type="text"
						value="${business.busRegNo}" disabled> <label
						class="form-label mt-4" for="readOnlyInput">사업자 이름</label> <input
						class="form-control" id="readOnlyInput3" type="text"
						value="${business.busName}" disabled>
					<label class="form-label mt-4" for="readOnlyInput">사업자 연락처</label> <input
						class="form-control" id="readOnlyInput4" type="text"
						value="${business.busTel}" disabled> <label
						class="form-label mt-4" for="readOnlyInput">사업자 이메일</label> <input
						class="form-control" id="readOnlyInput5" type="text"
						value="${business.busEmail}" disabled> <label
						class="form-label mt-4">기본주소</label> <br> <input
						class="form-control" id="readOnlyInput6" type="text"
						value="${business.busAddr1}" disabled> <br>
						<br> <label
						class="form-label mt-4">상세주소 </label> <input
						class="form-control" id="readOnlyInput6" type="text"
						value="${business.busAddr2}" disabled> <br>
				</div>
				</div>
	
	
<!-- 차단/해제 폼 -->
    <div class="row justify-content-center">
    <div class="card border-warning mb-3" style="max-width: 50rem;">
        <div class="card-body">
            <!-- Other fields -->
            <form id="blockForm" action="/admin/business/blockManager/details/${business.busId}" method="post">
                <input type="hidden" name="busId" value="${business.busId}">
                
                <div class="row mt-2">
                    <div class="col-4">차단 및 해제 사유</div>
                </div>
                <div class="col-8">
                    <input type="text" class="form-control" name="blockComment" id="blockComment" required>
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
    </div>
</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
