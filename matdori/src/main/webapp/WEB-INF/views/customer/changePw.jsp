<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/pwChange.js"></script>


 <form class="password-form" action="changePw" method="post" autocomplete="off">
		<div class="container w-400">
			<div class="row">
				<h2 class="mv-30">비밀번호 변경</h2>
			</div>
			
			<div class="row">
				<input class="form-input underline-input w-100 center" type="password" name="originPw" 
                placeholder="현재비밀번호">
			</div>
            
            <div class="row">
                <input class="form-input underline-input w-100 center" type="password" name="changePw" 
                placeholder="바꿀 비밀번호">
                <div class="success-feedback">올바른 비밀번호 형식입니다</div>
                <div class="fail-feedback">잘못된 비밀번호 형식입니다</div>
                <div class="fail2-feedback">현재 비밀번호와 일치합니다</div>
            </div>
                
          <div class="row">
                <input class="form-input underline-input w-100 center" type ="password" id="changePw-check" 
                placeholder="비밀번호 확인">
                <div class="success-feedback">비밀번호가 일치합니다</div>
                <div class="fail-feedback">바꿀 비밀번호와 일치하지 않습니다</div>
                <div class="fail2-feedback">바꿀 비밀번호를 먼저 작성하세요</div>
            </div>


            <div class="row">
                <button type="submit" class="btn btn-positive w-100 mt-20">변경하기</button>
            </div>
		</div>
		
	</form>
			<c:if test="${param.error !=null }">
				<div class="row important">
					<h3> 기존 비밀번호가 일치하지 않습니다</h3>
				</div>
			</c:if>


	

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>