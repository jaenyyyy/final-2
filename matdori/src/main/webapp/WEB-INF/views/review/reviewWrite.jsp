<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container">
	<div class="row justify-content-center" style="margin-top: 2%;">

		<div class="col-md-8">

			<div class="mt-4">
				<h1 class="text-start">
					<i class="fa-solid fa-pen-to-square" style="color: #ffb416;"></i>
					리뷰작성
				</h1>
			</div>

			<div class="mt-4">
				<!-- 별 -->
				<div class="rating" style="font-size: 30px; display: inline-block;">
					<i class="rating__star far fa-star" data-value="1"
						style="display: inline-block;"></i> <i
						class="rating__star far fa-star" data-value="2"
						style="display: inline-block;"></i> <i
						class="rating__star far fa-star" data-value="3"
						style="display: inline-block;"></i> <i
						class="rating__star far fa-star" data-value="4"
						style="display: inline-block;"></i> <i
						class="rating__star far fa-star" data-value="5"
						style="display: inline-block;"></i>
				</div>
			</div>

			<form action="reviewWrite" method="post" autocomplete="off"
				enctype="multipart/form-data">

				<div class="mt-4">
					<textarea name="reviewContent" rows="10" class="form-control"
						placeholder="내용을 입력해주세요."></textarea>
					<!-- 이미지를 업로드할 input 필드 -->
					<input type="file" name="attach" accept="image/*">
				</div>

				<!-- hidden field로 resNo 값을 전송 -->
				<input type="hidden" id="resNo" name="resNo" value="${param.resNo}">


				<!-- hidden field로 별점 값을 전송 -->
				<input type="hidden" id="reviewStarPoint" name="reviewStarPoint"
					value="5">

				<div class="text-end mt-4">
					<a class="btn btn-secondary btn-list"
						href="/customer/reviewListByCus">목록</a>
					<button class="btn btn-warning">작성</button>
				</div>
			</form>

		</div>

	</div>
</div>

<script>

	// URL에서 resNo 가져오기
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const resNo = urlParams.get('resNo');

	// resNo 값을 resNo 필드에 설정
	document.getElementById('resNo').value = resNo;


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

        stars.map((star, index) => {
            star.onclick = () => {
                i = stars.indexOf(star);

                if (star.className === starClassInactive) {
                    for (i; i >= 0; --i) {
                        stars[i].className = starClassActive;
                    }
                } else {
                    for (i; i < starsLength; ++i) {
                        stars[i].className = starClassInactive;
                    }
                }

                // 폼 필드에 별점 값 설정
                const selectedStars = index + 1;
                document.getElementById('reviewStarPoint').value = selectedStars;
            };
        });
    }

    executeRating(ratingStars);
</script>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
