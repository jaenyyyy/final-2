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
                <label>연락처</label>
                <input type="tel" name="customerContact" value="${customerDto.customerContact}" placeholder="010XXXXXXXX (- 없이)"
                        class="form-input underline-input w-100 mb-10">
                <div class="fail-feedback">전화번호 형식이 올바르지 않습니다</div>
            </div>

            <div class="row left">
                <label>생년월일</label>
                <input type="date" name="customerBirth" value="${customerDto.customerBirth}" 
                class="form-input underline-input w-100">
                <div class="fail-feedback">잘못된 날짜를 선택하셨습니다</div>
            </div>
            
            <div class="row left">
            	<input type="password" name="customerPw" class="form-input underline-input w-100 mv-20" placeholder="비밀번호 확인">
            </div>
            <div class="row">
                <button type="submit" class="btn btn-positive w-100">변경하기</button>
            </div>


        </div>

    </form>
    
			<c:if test="${param.error != null}">
		        <div class="row important">
					<span>입력하신 비밀번호가 일치하지 않습니다</span>
				</div>
			</c:if>
    
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>