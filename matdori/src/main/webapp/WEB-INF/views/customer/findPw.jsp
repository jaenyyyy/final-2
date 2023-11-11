<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script>
    $(document).ready(function(){
        $(".select").click(function(){
            $("#loading-icon").show(); // 로딩 아이콘 표시
        });
    });
</script>
<style>
	.loading-icon {
	    display: none;
	    position: fixed;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	    z-index: 9999;
	}
	
	.loading-icon img {
	    width: 50px; /* 아이콘의 너비 조절 */
	}
	

</style>
	<form action="findPw" method="post">
	
		<div class="container w-300">
		
			<div class="row">
				<h1>비밀번호 찾기</h1>
			</div>
			<div class="row">
				<input type="text" class="form-input w-100" name= "customerId" placeholder="아이디 입력">	
			</div>
			<div class="row">
				<input type="text" class="form-input w-100" name="customerEmail" placeholder="이메일 입력">
			</div>
			
			<div class="row">
				<button type="submit" class="btn btn-positive w-100 select">비밀번호 찾기</button>				
			</div>
			
			<div id="loading-icon" class="loading-icon">
			    <i class="fa-solid fa-spinner fa-spin fa-6x black"></i>
			</div>
		</div>
		
	</form>
	

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
