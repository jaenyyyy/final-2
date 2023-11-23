<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<form action="reviewWrite" method="post" enctype="multipart/form-data">
	<div class="row justify-content-center">
		<div class="col-md-4">

			<h1>Write a Review</h1>

			<div class="row left">
			<label
			class="form-label mt-4" for="readOnlyInput">차단일</label> <input
			class="form-control mb-4" id="readOnlyInput3" type="text"
			value="${reviewDto.reviewWriter}" disabled>
	</div>
 <label> 방문식당 <i class="fa-solid fa-asterisk red"></i>
				</label> <label>예약날짜 <i class="fa-solid fa-asterisk red"></i>
				</label> <label> 작성자 <i class="fa-solid fa-asterisk red"></i>
				</label>
			</div>



			<label>리뷰 내용 <i class="fa-solid fa-asterisk red"></i></label>
			<textarea name="reviewContent" rows="4" cols="50">  </textarea>
			<br> 리뷰 내용은 반드시 작성해주세요 <br> 
			
			<br>

			<div class="row right">
				<span class="len red">0</span> / 1000
			</div>

			<br> <br> <label>사진 첨부</label><br> <input type="file"
				name="attach"><br> <br> <br>
		</div>
	</div>





	<div class="row right"></div>
	<button type="submit" class="btn btn-positive">
		<i class="fa-solid fa-pen"></i> 작성
	</button>
	<a href="/customer/reviewListByCus" class="btn"> <i
		class="fa-solid fa-list"></i> 목록</a> 
		<a
		href="/customer/delete?reviewNo=${reviewDto.reviewNo}"
		class="btn btn-negative btn-small">삭제하기</a>
		
		
</form>


