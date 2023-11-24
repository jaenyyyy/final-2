<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<style>
  .container {
    max-width: 500px; /* 변경 가능한 폭 설정 */
    margin: 0 auto; /* 가운데 정렬 */
  }
  
  .tag-none{
    text-decoration: none;
    color: black;
   }
  .line{
     border-top: 1px solid #ffb416;
   }
   .tag-none:hover {
    color: #FF3300; /* 원하는 호버 시 색상으로 변경 */
  }
</style>


<form action="login" method="post" autocomplete="off">
	<div class="container justify-content-center" style="margin-bottom:6%;">
	
		<!-- 제목 -->
		<div class="row" style="margin-top:20%;">
			<h1>
				<i class="fa-solid fa-circle-user" style="color: #ffb416;"></i>
				Login
			</h1>
		</div>
		
		<div class="row line"></div>
		
		<!-- 아이디 -->
		<div class="row mt-4">
			<input class="form-control" type="text" name="customerId" placeholder="아이디" required>
		</div>
		
		<!-- 비밀번호 -->
		<div class="row mt-4">
			<input type="password" name="customerPw" class="form-control" placeholder="비밀번호" required>
		</div>
		
		<!-- 버튼 -->
		<div class="row mv-30 mt-4"> 
			<button type="submit" class="btn btn-warning text-black">
				로그인
			</button>
		</div>
		
		<div class="row line mt-4"></div>
		
		<!-- 링크 -->
		<div class="row text-center mt-4">
			<div class="col">
				<a href="/customer/join" class="tag-none">회원가입 하기</a> <br>
			</div>
			<div class="col">
				<a href="/customer/findPw" class="tag-none">비밀번호 찾기</a>
			</div>
		</div>
	</div>


</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


