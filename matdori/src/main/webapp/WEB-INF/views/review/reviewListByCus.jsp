<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<style>
.row-top {
	margin-top: 20%;
}

.page-line {
	border-right: 3px solid #ffb416;
}

.bold {
	font-weight: bold;
}

.menu-tag {
	text-decoration: none;
	color: black;
}

.menu-tag:hover {
	color: #FFB416;
}

.res-line {
	border-top: 1px solid #ffb416;
	border-bottom: 1px solid #ffb416;
	width: 70%;
}

.rating {
	width: 100%;
}

.rating__star {
	cursor: pointer;
	color: #FFB416;
}
</style>

<div class="container">
	<div class="row" style="margin-top: 5%">




		<!-- 마이페이지 탭 -->
		<div class="col-3 page-line">
			<div class="row">

				<!--  제목  -->
				<div class="row mb-4">
					<h2 class="bold">
						<a class="menu-tag" href="mypage"> <i class="fa-solid fa-user"
							style="color: #ffb416;"></i> 마이페이지
						</a>
					</h2>
				</div>
				<br> <br> <br> <br> <br> <br>


				<h4 class="bold">
					<a class="menu-tag" href="pick">북마크</a>
				</h4>
			</div>
			<div class="row row-top">
				<h4 class="bold">
					<a class="menu-tag" href="rezList">예약내역</a>
				</h4>
			</div>
			<div class="row row-top">
				<h4 class="bold">
					<a class="menu-tag" href="reviewListByCus">나의리뷰</a>
				</h4>
			</div>
		</div>


		<!-- 주 내용 -->
		<div class="col-9">
			<div class="row">

				<br> <br>
				<h4 class="bold">나의 리뷰</h4>
				<c:forEach var="reviewDto" items="${reviewList}">
					<div>
						<br>
					</div>
					<div class="row res-line p-4" style="margin-left: 100px;">
						<div class="col-3">
							<!-- 사진자리 -->
							<c:choose>
								<c:when test="${not empty reviewDto}">
									<img src="image?reviewNo=${reviewDto.reviewNo}" width="100"
										height="100">
								</c:when>
								<c:otherwise>
									<img src="https://dummyimage.com/100x100/000/fff">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-7">
							<div class="row modal-title">
								<span> ${reviewDto.reviewWriteDate} <span
									class="rating__star ${reviewDto.reviewStarPoint >= 1 ? 'fas' : 'far'} fa-star"></span>
									<span
									class="rating__star ${reviewDto.reviewStarPoint >= 2 ? 'fas' : 'far'} fa-star"></span>
									<span
									class="rating__star ${reviewDto.reviewStarPoint >= 3 ? 'fas' : 'far'} fa-star"></span>
									<span
									class="rating__star ${reviewDto.reviewStarPoint >= 4 ? 'fas' : 'far'} fa-star"></span>
									<span
									class="rating__star ${reviewDto.reviewStarPoint >= 5 ? 'fas' : 'far'} fa-star"></span>
								</span>
							</div>

							<div class="row">${reviewDto.reviewContent}</div>
						</div>
						<div class="col-2 text-end">
							<div class="row mb-3">
								<a href="/restaurant/detail?resNo=${reviewDto.resNo}"
									class="menu-tag">매장가기</a>
								<form action="/customer/delete" method="post">
									<input type="hidden" name="reviewNo"
										value="${reviewDto.reviewNo}">
									<button type="submit" class="menu-tag"
										style="background: none; border: none; color: red;">
										리뷰 삭제</button>
								</form>
							</div>
						</div>

					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>


<script>

	
	
	// 별찍기
	$(document).ready(function() {
	    executeRating(ratingStars);
	});

	const ratingStars = [...document.getElementsByClassName("rating__star")];

	function executeRating(stars) {
	  const starClassActive = "rating__star fas fa-star";
	  const starClassInactive = "rating__star far fa-star";
	  const starsLength = stars.length;
	  let i;

	  /* stars.map((star) => {
	    star.onclick = () => {
	      i = stars.indexOf(star);

	      if (star.className===starClassInactive) {
	        for (i; i >= 0; --i) stars[i].className = starClassActive;
	      } else {
	        for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
	      }
	    }; */
	  });
	}

	executeRating(ratingStars);




</script>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>