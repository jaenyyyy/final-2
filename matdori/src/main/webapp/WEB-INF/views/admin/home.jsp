<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="row text-center">
	<div class="mt-5">
		<div class="mb-5">
    		<h1><i class="fa-solid fa-house" style="color: #ffb416;"></i> 관리자 HOME</h1>
    	</div>
	</div>
    <div class="col-6">
        <div class="alert alert-dismissible alert-warning">
            <a class="nav-link" href="#">이용자 관리</a>
        </div>
    </div>
    <div class="col-6">
        <div class="alert alert-dismissible alert-warning">
            <a class="nav-link" href="#">사업체 관리</a>
        </div>
    </div>
    <div class="col-6">
        <div class="alert alert-dismissible alert-warning">
            <a class="nav-link" href="/admin/business/judge/list">사업체 심사</a>
        </div>
    </div>
    <div class="col-6">
        <div class="alert alert-dismissible alert-warning">
            <a class="nav-link" href="/admin/restaurant/list">매장 관리</a>
        </div>
    </div>
</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>