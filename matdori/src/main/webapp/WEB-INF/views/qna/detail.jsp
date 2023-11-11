<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    
<div class="container w-800">
   <div class="row">
   	
   	
   		<div class="col">
      		<h1>Q&A</h1>
      	</div>

	   <div class="col">
	      <div>
		      <h4>제목 : ${qnaDto.qnaTitle}</h4>
		      <hr class="w-100 hr-style">
	      </div>
	      <div>
	         <span>카테고리 : ${qnaDto.qnaCategory}</span>
	      </div>
	      
	      <div>
	         <span>내용 : ${qnaDto.qnaAnswer}</span>
	      </div>
	      
	   </div>
   
	   <div class="col">
	   		<a class="btn btn-warning" href="edit?qnaNo=${qnaDto.qnaNo}">
	   		수정
	   		</a>
	   		<a class="btn btn-warning" href="delete?qnaNo=${qnaDto.qnaNo}">
	   		삭제
	   		</a>
	   		<a class="btn btn-warning" href="list">
	   		목록
	   		</a>
	   </div>
   </div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>