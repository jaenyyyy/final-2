<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 
 <form action="edit" method="post">
 <div class="row">
 	<div class="col text-center">
 		<input type="hidden" name="qnaNo" value="${qnaDto.qnaNo}">
 	
 		<div>
 			<h1>Q&A 수정</h1>
 		</div>
 		
		<div>
			<c:choose>
				<c:when test="${qnaDto.qnaCategory == '기타'}">
					<select name="qnaCategory">
						<option value="이용안내" >이용안내</option>
					    <option value="결제안내">결제안내</option>
					    <option value="취소안내">취소안내</option>
					    <option value="예약안내">예약안내</option>
					    <option value="기타" selected>기타</option>
					</select>
				</c:when>
				<c:when test="${qnaDto.qnaCategory == '결제안내'}">
					<select name="qnaCategory">
						<option value="이용안내">이용안내</option>
					    <option value="결제안내" selected>결제안내</option>
					    <option value="취소안내">취소안내</option>
					    <option value="예약안내">예약안내</option>
					    <option value="기타">기타</option>
					</select>
				</c:when>
				<c:when test="${qnaDto.qnaCategory == '취소안내'}">
					<select name="qnaCategory">
						<option value="이용안내">이용안내</option>
					    <option value="결제안내">결제안내</option>
					    <option value="취소안내" selected>취소안내</option>
					    <option value="예약안내">예약안내</option>
					    <option value="기타">기타</option>
					</select>
				</c:when>
				<c:when test="${qnaDto.qnaCategory == '예약안내'}">
					<select name="qnaCategory">
						<option value="이용안내">이용안내</option>
					    <option value="결제안내">결제안내</option>
					    <option value="취소안내">취소안내</option>
					    <option value="예약안내" selected>예약안내</option>
					    <option value="기타">기타</option>
					</select>
				</c:when>
				<c:otherwise>
					<select name="qnaCategory">
						<option value="이용안내">이용안내</option>
					    <option value="결제안내">결제안내</option>
					    <option value="취소안내">취소안내</option>
					    <option value="예약안내">예약안내</option>
					    <option value="기타">기타</option>
					</select>
				</c:otherwise>
			</c:choose>
		</div>


		
		
 		
 		<div>
 			제목
 			<input class="form-control" type="text" name="qnaTitle" value="${qnaDto.qnaTitle}">
 		</div>
 		
 		<div>
 			내용
			<textarea class="form-control" name="qnaAnswer">${qnaDto.qnaAnswer}</textarea>
 		</div>
 		
 		<div>
 			<button type="submit" class="btn btn-warning">수정하기</button>
 		</div>
 	</div>
 </div>
 </form>
 
 
 
 
 
 
 
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>