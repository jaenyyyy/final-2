<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.text-badge {
	font-weight: bold;
	color: black;
}

.list-border {
	border: 2px solid #ffb416;
	border-radius: 10px;
	padding: 20px;
}

.res-line {
	border-top: 2px solid #ffb416;
	border-bottom: 2px solid #ffb416;
	width: 100%;
}

.star-avg {
	font-weight: bold;
	color: black;
	font-size: 25px;
}

.main-images {
	max-width: 100%;
	height: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.sub-images {
	max-width: 50%;
	height: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.info-margin {
	margin-bottom: 40px;
	margin-top: 40px;
}

.under-line {
	text-decoration: none;
	color: black;
}

.box {
	width: 200px;
	height: 200px;
	border: 2px solid #000000;
}

.rating {
	width: 100%;
}

.rating__star {
	cursor: pointer;
	color: #FFB416;
}
</style>



<!-- 리뷰 -->
<div class="container" id="fullPageContent">
	<div
		class="row justify-content-center text-center list-border info-margin"
		id="reviewSection">
		<!-- 제목 -->
		<div class="row justify-content-between align-items-center">
			<div class="col-6">
				<h3>
					<span class="badge rounded-pill bg-warning text-black">리뷰</span>
				</h3>
			</div>
			<div class="col-6 text-end">
				<a href="http://localhost:8080/restaurant/detail?resNo=${param.resNo}" class="btn btn-primary">매장으로</a>
			</div>
		</div>

		<div class="row text-start ">
			<div class="row">
				<div class="col">
					<c:choose>
						<c:when test="${customerDto.customerLevel == '어쩌고수저'}">
							<i class="fa-solid fa-spoon" style="color: #b5b5b5;"></i>
						</c:when>
					</c:choose>
					${reviewDto.reviewWriter}
				</div>
			</div>
			<div class="row">
				<c:forEach var="review" items="${reviewListByRes}">
					<div class="col-2">
						<img
							src="http://localhost:8080/customer/image?reviewNo=${review.reviewNo}"
							width="100" height="100" class="">
					</div>
					<div class="col-10 text-left">
						<span> <span
							class="rating__star ${review.reviewStarPoint >= 1 ? 'fas' : 'far'} fa-star"></span>
							<span
							class="rating__star ${review.reviewStarPoint >= 2 ? 'fas' : 'far'} fa-star"></span>
							<span
							class="rating__star ${review.reviewStarPoint >= 3 ? 'fas' : 'far'} fa-star"></span>
							<span
							class="rating__star ${review.reviewStarPoint >= 4 ? 'fas' : 'far'} fa-star"></span>
							<span
							class="rating__star ${review.reviewStarPoint >= 5 ? 'fas' : 'far'} fa-star"></span>
							${review.reviewWriteDate}
						</span> <br> ${review.reviewContent}
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
