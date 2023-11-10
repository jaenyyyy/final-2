<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 
 <form action="edit" method="post">
 <div class="row">
 	<div class="col text-center">
 		<input type="hidden" name="noticeNo" value="${noticeDto.noticeNo}">
 	
 		<div>
 			<h1>공지사항 수정</h1>
 		</div>
 		
 		<div>
 			제목
 			<input class="form-control" type="text" name="noticeTitle" value="${noticeDto.noticeTitle}">
 		</div>
 		
 		<div>
 			내용
			<textarea class="form-control" name="noticeContent">${noticeDto.noticeContent}</textarea>
 		</div>
 		
 		<div>
 			<button type="submit" class="btn btn-warning">수정하기</button>
 		</div>
 	</div>
 </div>
 </form>
 
 
 
 
 
 
 
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>