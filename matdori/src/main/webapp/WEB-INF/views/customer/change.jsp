<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="change" method="post" autocomplete="off">
	<div class="container w-500">
        <div class="row">
            <h1>개인정보 변경</h1>
        </div>
        <div class="row left">
            <label>
              	이름 : ${customerDto.customerName}
			</label>
        </div>
        
        <div class="row left">
        <label>
        	비밀번호 : ${customerDto.customerPw}
        </label>
        	
        </div>
        
        <div class="row left">
        <label>이메일 : </label> 
            <input type="email" name="customerEmail" class="form-input w-100"
					value="${customerDto.customerEmail}" placeholder="testuser@kh.com">
        </div>
        
        <div class="row left">
        <label>연락처 : </label>
            <input type="tel" name="customerContact" class="form-input w-100"
					value="${customerDto.customerContact}" placeholder="- 제외하고 입력">
        </div>
       
        
        
        <div>
        	<label>비밀번호 확인 : </label>
        	<input type="password" name="customerPw" required class="form-input w-100">
        </div>
        
        <div class="row">
            <button type="submit" class="btn btn-positive w-100">정보변경</button>
        </div>
        
        <c:if test="${param.error != null}">
	        <div class="row red">
				<h3>입력하신 비밀번호가 일치하지 않습니다</h3>
			</div>
		</c:if>
    </div>
    
    
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>