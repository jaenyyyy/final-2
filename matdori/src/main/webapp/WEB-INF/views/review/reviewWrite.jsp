<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="write" method="post" autocomplete="off">
	<div class="container w-600">
		<div class="row">
			<h2 class="mv-30">review 등록</h2>
		</div>
		<div class="float-container">
			<div class="float-left">
				<select class="form-input underline-input" name="faqCategory" style="width:150px; padding-left:35px"
								value="${reviewDto.reviewWriter}">
					<option>맛있는</option>
					<option>매장의</option>
					<option>주문이였다</option>
					<option>기타</option>
				</select>
			</div>
			<div class="float-right">
				<input class="form-input underline-input" type="text" name="faqTitle" style="width:440px; padding-left:20px;"
					value="${reviewDto.reviewContent}" required placeholder="제목">
			</div>
		</div>
		
		<hr class="mt-10" style="border:1px; background-color: #BEADFA;">
		
		<div class="row left">
			<textarea class="form-input w-100" name="faqContent"
				style="min-height: 250px; border:none;" required></textarea>
		</div>
		<hr class="mb-20" style="border:1px; background-color: #BEADFA;">

		<div class="row">
			<button type="submit" class="btn btn-positive w-100">작성</button>
		</div>
	</div>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>