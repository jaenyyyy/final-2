<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
  .container {
    max-width: 700px; /* 변경 가능한 폭 설정 */
    margin: 0 auto; /* 가운데 정렬 */
  }
 </style>


<form action="change" method="post" autocomplete="off">
	<div class="container">
		<div class="row" style="margin-top: 10%">
		
		
			<!-- 제목 -->
			<div class="row mb-4">
				<h1 class="bold">
					<i class="fa-solid fa-user" style="color: #ffb416;"></i> 개인정보 변경
				</h1>
			</div>
			
			<!-- 정보변경 -->
			<div class="row">
				<div class="form-group">
				  <fieldset disabled="">
				    <label class="form-label" for="disabledInput">이름</label>
				    <input class="form-control" id="disabledInput" type="text" placeholder="${customerDto.customerName}" disabled="">
				  </fieldset>
				</div>
				
				<div class="form-group">
				  <label class="col-form-label mt-4" for="inputDefault">연락처</label>
				  <input type="tel" name="customerContact" class="form-control" value="${customerDto.customerContact}" placeholder="( - ) 제외" id="inputDefault">
				  <div class="fail-feedback">전화번호 형식이 올바르지 않습니다</div>
				</div>
				
				<div class="form-group">
				  <label class="col-form-label mt-4" for="inputDefault">생년월일</label>
				  <input type="date" name="customerBirth" class="form-control" value="${customerDto.customerBirth}">
				  <div class="fail-feedback">잘못된 날짜를 선택하셨습니다</div>
				</div>
				
				<div class="form-group mb-4">
				  <label class="col-form-label mt-4" for="inputDefault">비밀번호 확인</label>
				  <input type="password" name="customerPw" class="form-control" placeholder="비밀번호를 입력해주세요">
				</div>
				
				
				<!-- 버튼 -->
				<div class="row ms-1 mt-2">
					<button type="submit" class="btn btn-warning">변경하기</button>
				</div>
				
				<c:if test="${param.error != null}">
					<div class="row important">
						<span>입력하신 비밀번호가 일치하지 않습니다</span>
					</div>
				</c:if>
			</div>
			
			
			
		</div>
	</div>
</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>