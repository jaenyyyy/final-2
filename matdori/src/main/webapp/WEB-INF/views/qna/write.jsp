Qna write
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
<form action="write" method="post" autocomplete="off">
	<div class="container">
		<div class="row justify-content-center" style="margin-top:2%;">
			
			<div class="col-md-8">
			
				<div class="mt-4">
                    <h1 class="text-start">
                        <i class="fa-solid fa-pen-to-square" style="color: #ffb416;"></i>
                       <span
					style="font-weight: bold;"> Q & A 작성</span><br><br>
                    </h1>
                </div>
	                
                <div class="mt-4">
                	<select name="qnaCategory" class="form-select">
					    <option value="이용안내">이용안내</option>
					    <option value="결제안내">결제안내</option>
					    <option value="취소안내">취소안내</option>
					    <option value="예약안내">예약안내</option>
					    <option value="기타">기타</option>
				  	</select>
                </div>
                
                <div class="mt-4">
                	<input type="text" name="qnaTitle" class="form-control" placeholder="제목을 입력해주세요.">
                </div>
              
              	<div class="mt-4">
              		<textarea name="qnaAnswer" rows="10" class="form-control" placeholder="내용을 입력해주세요."></textarea>
              	</div>
             
				<div class="text-end mt-4">
                	<a class="btn btn-secondary" href="/qna/list">목록</a>
                    <button class="btn btn-warning">작성</button>
               	</div>
                
			</div>

	   
	   </div>
	</div>
</form>

 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

